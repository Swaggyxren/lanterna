package com.swaggyxren.lanterna.domain.server

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.IBinder
import androidx.core.app.NotificationCompat
import dagger.hilt.android.AndroidEntryPoint

/**
 * Foreground service that hosts a Minecraft Java Edition server JVM.
 *
 * Skeleton for milestone 4. Currently it just stays alive with a notification so
 * we can validate the foreground-service-special-use plumbing end-to-end.
 *
 * Final implementation will spawn `ProcessBuilder("$jreHome/bin/java", ...)`,
 * pipe stdin/stdout, and route logs/commands to a ViewModel via a SharedFlow.
 */
@AndroidEntryPoint
class ServerHostService : Service() {

    override fun onBind(intent: Intent?): IBinder? = null

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        ensureChannel()
        startForeground(NOTIFICATION_ID, buildNotification())
        return START_STICKY
    }

    private fun ensureChannel() {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.O) return
        val nm = getSystemService(NotificationManager::class.java)
        if (nm.getNotificationChannel(CHANNEL_ID) != null) return
        val channel = NotificationChannel(
            CHANNEL_ID,
            "Server status",
            NotificationManager.IMPORTANCE_LOW
        ).apply {
            description = "Shown while a Minecraft server is running on this device."
            setShowBadge(false)
        }
        nm.createNotificationChannel(channel)
    }

    private fun buildNotification(): Notification =
        NotificationCompat.Builder(this, CHANNEL_ID)
            .setContentTitle("Lanterna server running")
            .setContentText("Tap to manage")
            .setSmallIcon(android.R.drawable.stat_sys_warning) // placeholder until icon is wired
            .setOngoing(true)
            .setSilent(true)
            .build()

    companion object {
        private const val CHANNEL_ID = "lanterna_server_status"
        private const val NOTIFICATION_ID = 1001

        fun start(context: Context) {
            val intent = Intent(context, ServerHostService::class.java)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                context.startForegroundService(intent)
            } else {
                context.startService(intent)
            }
        }

        fun stop(context: Context) {
            context.stopService(Intent(context, ServerHostService::class.java))
        }
    }
}
