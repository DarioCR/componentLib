package com.example.componentlib.components.button

import androidx.compose.runtime.mutableStateOf
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class ButtonAccessibilityTest {

    @Test
    fun shouldAllowInteraction_allowsFirstEventAndUpdatesTimestamp() {
        val lastInteraction = mutableStateOf(0L)

        val allowed = lastInteraction.shouldAllowInteraction(throttleIntervalMillis = 600L) { 1_000L }

        assertTrue(allowed)
        assertEquals(1_000L, lastInteraction.value)
    }

    @Test
    fun shouldAllowInteraction_blocksEventsInsideWindow() {
        val lastInteraction = mutableStateOf(5_000L)

        val allowed = lastInteraction.shouldAllowInteraction(throttleIntervalMillis = 600L) { 5_300L }

        assertFalse(allowed)
        assertEquals(5_000L, lastInteraction.value)
    }

    @Test
    fun shouldAllowInteraction_resetsAfterInterval() {
        val lastInteraction = mutableStateOf(10_000L)

        val firstAttempt = lastInteraction.shouldAllowInteraction(throttleIntervalMillis = 600L) { 10_500L }
        val secondAttempt = lastInteraction.shouldAllowInteraction(throttleIntervalMillis = 600L) { 11_000L }

        assertFalse(firstAttempt)
        assertTrue(secondAttempt)
        assertEquals(11_000L, lastInteraction.value)
    }
}
