package com.example.componentlib.components.button

// Pruebas unitarias de los tokens de color usados por AppIconButton y AppToggleIconButton.
import androidx.compose.ui.graphics.Color
import org.junit.Assert.assertEquals
import org.junit.Test

class AppIconButtonTokensTest {

    // Verifica que la paleta del icon button coincida con el diseño.
    @Test
    fun iconButtonColors_matchDesignSystemPalette() {
        val colors = ButtonTokens.iconButtonColors()

        assertEquals(Color(0xFFE7EDFF), colors.containerColor)
        assertEquals(Color(0xFF1C3FAA), colors.contentColor)
        assertEquals(Color(0xFFE4E7EC), colors.disabledContainerColor)
        assertEquals(Color(0xFF8A94A7), colors.disabledContentColor)
    }

    // Verifica que el estado checked use la paleta encendida.
    @Test
    fun toggleIconButtonColors_useCheckedPaletteWhenOn() {
        val colors = ButtonTokens.toggleIconButtonColors(checked = true)

        assertEquals(Color(0xFF1A4FE3), colors.containerColor)
        assertEquals(Color(0xFFFFFFFF), colors.contentColor)
        assertEquals(Color(0xFF9CB7FF), colors.disabledContainerColor)
        assertEquals(Color(0xB3FFFFFF), colors.disabledContentColor)
    }

    // Verifica que el estado apagado use la paleta neutral.
    @Test
    fun toggleIconButtonColors_fallBackToNeutralPaletteWhenOff() {
        val colors = ButtonTokens.toggleIconButtonColors(checked = false)

        assertEquals(Color(0xFFE7EDFF), colors.containerColor)
        assertEquals(Color(0xFF1C3FAA), colors.contentColor)
        assertEquals(Color(0xFFE4E7EC), colors.disabledContainerColor)
        assertEquals(Color(0xFF8A94A7), colors.disabledContentColor)
    }
}
