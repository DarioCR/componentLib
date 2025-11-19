package com.example.componentlib.components.button

import android.os.SystemClock
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.role
import androidx.compose.ui.semantics.semantics

private const val DefaultButtonThrottleIntervalMillis = 600L

/**
 * Applies a default TalkBack role and optional content description to clickable containers.
 *
 * @param role Accessibility role exposed to TalkBack (defaults to [Role.Button]).
 * @param contentDescription Optional description read by TalkBack.
 * @param mergeDescendants When true combines descendant semantics with the surface.
 */
internal fun Modifier.buttonSemantics(
    role: Role = Role.Button,
    contentDescription: String? = null,
    mergeDescendants: Boolean = true
): Modifier = semantics(mergeDescendants = mergeDescendants) {
    this.role = role
    if (contentDescription != null) {
        this.contentDescription = contentDescription
    }
}

/**
 * Remembers a lambda that ignores clicks faster than [throttleIntervalMillis] to avoid tap spam.
 */
@Composable
internal fun rememberThrottledClick(
    enabled: Boolean,
    throttleIntervalMillis: Long = DefaultButtonThrottleIntervalMillis,
    onClick: () -> Unit
): () -> Unit {
    val latestOnClick = rememberUpdatedState(onClick)
    val lastInteractionTimestamp = remember { mutableStateOf(0L) }

    return remember(enabled, throttleIntervalMillis) {
        throttled@{
            if (!enabled) return@throttled
            if (lastInteractionTimestamp.shouldAllowInteraction(throttleIntervalMillis)) {
                latestOnClick.value()
            }
        }
    }
}

/**
 * Similar to [rememberThrottledClick] but forwards checked state changes for toggleable surfaces.
 */
@Composable
internal fun rememberThrottledOnCheckedChange(
    enabled: Boolean,
    throttleIntervalMillis: Long = DefaultButtonThrottleIntervalMillis,
    onCheckedChange: (Boolean) -> Unit
): (Boolean) -> Unit {
    val latestOnCheckedChange = rememberUpdatedState(onCheckedChange)
    val lastInteractionTimestamp = remember { mutableStateOf(0L) }

    return remember(enabled, throttleIntervalMillis) {
        throttled@{ checked ->
            if (!enabled) return@throttled
            if (lastInteractionTimestamp.shouldAllowInteraction(throttleIntervalMillis)) {
                latestOnCheckedChange.value(checked)
            }
        }
    }
}

/**
 * Returns true when enough time has elapsed since the last interaction and updates the timestamp.
 */
internal fun MutableState<Long>.shouldAllowInteraction(
    throttleIntervalMillis: Long,
    timeProvider: () -> Long = { SystemClock.elapsedRealtime() }
): Boolean {
    val currentTime = timeProvider()
    if (currentTime - value >= throttleIntervalMillis) {
        value = currentTime
        return true
    }
    return false
}
