package com.example.componentlib.components.button

import androidx.compose.ui.graphics.Color
import org.junit.Assert.assertEquals
import org.junit.Test

class AppIconButtonTokensTest {

    @Test
    fun iconButtonColors_matchDesignSystemPalette() {
        val colors = ButtonTokens.iconButtonColors()

        assertEquals(Color(0xFFE7EDFF), colors.containerColor)
        assertEquals(Color(0xFF1C3FAA), colors.contentColor)
        assertEquals(Color(0xFFE4E7EC), colors.disabledContainerColor)
        assertEquals(Color(0xFF8A94A7), colors.disabledContentColor)
    }

    @Test
    fun toggleIconButtonColors_useCheckedPaletteWhenOn() {
        val colors = ButtonTokens.toggleIconButtonColors(checked = true)

        assertEquals(Color(0xFF1A4FE3), colors.containerColor)
        assertEquals(Color(0xFFFFFFFF), colors.contentColor)
        assertEquals(Color(0xFF9CB7FF), colors.disabledContainerColor)
        assertEquals(Color(0xB3FFFFFF), colors.disabledContentColor)
    }

    @Test
    fun toggleIconButtonColors_fallBackToNeutralPaletteWhenOff() {
        val colors = ButtonTokens.toggleIconButtonColors(checked = false)

        assertEquals(Color(0xFFE7EDFF), colors.containerColor)
        assertEquals(Color(0xFF1C3FAA), colors.contentColor)
        assertEquals(Color(0xFFE4E7EC), colors.disabledContainerColor)
        assertEquals(Color(0xFF8A94A7), colors.disabledContentColor)
    }
}
