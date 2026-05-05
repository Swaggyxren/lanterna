package com.swaggyxren.lanterna.util

import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

/**
 * Cancelable, progress-reporting unit of work.
 *
 * Inspired by ZalithLauncher2's `Task.runTask` pattern. Used for:
 * - JRE downloads (with progress + speed)
 * - Server flavor downloads
 * - Forge / NeoForge installer runs
 * - Anything that takes more than ~1s and the user should be able to cancel.
 *
 * Skeleton — full implementation lands in milestone 2.
 */
class Task<R> private constructor(
    val id: String,
    private val dispatcher: CoroutineDispatcher,
    private val block: suspend Task<R>.() -> R
) {
    private val _state = MutableStateFlow<State<R>>(State.Pending)
    val state: StateFlow<State<R>> = _state.asStateFlow()

    private val _progress = MutableStateFlow(Progress.None)
    val progress: StateFlow<Progress> = _progress.asStateFlow()

    private var job: Job? = null

    fun start(scope: CoroutineScope) {
        if (job?.isActive == true) return
        _state.value = State.Running
        // Launch directly on the caller's scope. The block already catches
        // every Throwable (rethrowing CancellationException so cancellation
        // still propagates), so no exception escapes to the parent — there
        // is nothing to isolate with a SupervisorJob, and inserting one
        // would just leak an unfinished CompletableJob into the scope.
        job = scope.launch(dispatcher) {
            try {
                val result = block()
                _state.value = State.Succeeded(result)
            } catch (ce: CancellationException) {
                _state.value = State.Canceled
                throw ce
            } catch (t: Throwable) {
                _state.value = State.Failed(t)
            }
        }
    }

    fun cancel() { job?.cancel() }

    fun report(done: Long, total: Long, speedBytesPerSec: Long = 0L) {
        _progress.value = Progress(done, total, speedBytesPerSec)
    }

    sealed interface State<out R> {
        data object Pending : State<Nothing>
        data object Running : State<Nothing>
        data class Succeeded<R>(val value: R) : State<R>
        data class Failed(val cause: Throwable) : State<Nothing>
        data object Canceled : State<Nothing>
    }

    data class Progress(val done: Long, val total: Long, val speedBytesPerSec: Long) {
        val ratio: Float get() = if (total > 0) (done.toFloat() / total.toFloat()).coerceIn(0f, 1f) else 0f
        companion object { val None = Progress(0, 0, 0) }
    }

    companion object {
        fun <R> runTask(
            id: String,
            dispatcher: CoroutineDispatcher = Dispatchers.IO,
            task: suspend Task<R>.() -> R
        ): Task<R> = Task(id, dispatcher, task)
    }
}
