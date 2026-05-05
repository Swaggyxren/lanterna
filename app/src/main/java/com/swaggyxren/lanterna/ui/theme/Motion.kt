package com.swaggyxren.lanterna.ui.theme

import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.IntSize

/**
 * The "slightly bouncy" spring system used across Lanterna.
 *
 * 95% of animations should pull from one of these tokens so the feel stays
 * consistent. Avoid hand-crafting one-off spring specs in screen code.
 */
object Motion {

    /** Default — used for FAB transforms, page transitions, status pill morphs. */
    val Bouncy = spring<Float>(
        dampingRatio = Spring.DampingRatioMediumBouncy,
        stiffness = Spring.StiffnessMediumLow
    )

    /** Sizes that shouldn't overshoot too aggressively (bottom sheets, list items). */
    val SoftBounceSize = spring<IntSize>(
        dampingRatio = Spring.DampingRatioLowBouncy,
        stiffness = Spring.StiffnessMediumLow
    )

    /** Offsets — used by AnimatedContent slide transitions. */
    val SoftBounceOffset = spring<IntOffset>(
        dampingRatio = Spring.DampingRatioLowBouncy,
        stiffness = Spring.StiffnessMediumLow
    )

    /** Snappy — for fast tap-feedback like FAB scale. */
    val Snappy = spring<Float>(
        dampingRatio = Spring.DampingRatioMediumBouncy,
        stiffness = Spring.StiffnessHigh
    )
}
