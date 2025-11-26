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

// Aplica un rol de TalkBack por defecto y una contentDescription opcional a contenedores clicables.
// role: Rol de accesibilidad expuesto a TalkBack (por defecto Role.Button).
// contentDescription: Descripción opcional leída por TalkBack.
// mergeDescendants: Cuando es true combina la semántica de los descendientes con la superficie.
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

// Recuerda una lambda que ignora clics más rápidos que throttleIntervalMillis para evitar taps repetidos.
@Composable
internal fun rememberThrottledClick(
    enabled: Boolean,
    throttleIntervalMillis: Long = DefaultButtonThrottleIntervalMillis,
    onClick: () -> Unit,
    timeSource: () -> Long = { SystemClock.elapsedRealtime() }
): () -> Unit {
    val latestOnClick = rememberUpdatedState(onClick)
    val lastInteractionTimestamp = remember { mutableStateOf(0L) }

    return remember(enabled, throttleIntervalMillis, timeSource) {
        throttled@{
            if (!enabled) return@throttled
            if (lastInteractionTimestamp.shouldAllowInteraction(throttleIntervalMillis, timeSource)) {
                latestOnClick.value()
            }
        }
    }
}

// Similar a rememberThrottledClick pero propagando cambios de estado checked para superficies con toggle.
@Composable
internal fun rememberThrottledOnCheckedChange(
    enabled: Boolean,
    throttleIntervalMillis: Long = DefaultButtonThrottleIntervalMillis,
    onCheckedChange: (Boolean) -> Unit,
    timeSource: () -> Long = { SystemClock.elapsedRealtime() }
): (Boolean) -> Unit {
    val latestOnCheckedChange = rememberUpdatedState(onCheckedChange)
    val lastInteractionTimestamp = remember { mutableStateOf(0L) }

    return remember(enabled, throttleIntervalMillis, timeSource) {
        throttled@{ checked ->
            if (!enabled) return@throttled
            if (lastInteractionTimestamp.shouldAllowInteraction(throttleIntervalMillis, timeSource)) {
                latestOnCheckedChange.value(checked)
            }
        }
    }
}

// Devuelve true cuando ha pasado suficiente tiempo desde la última interacción y actualiza el timestamp.
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
