package com.example.componentlib.components.textfield.tokens

import androidx.compose.ui.graphics.Color
import com.example.componentlib.components.textfield.AppTextFieldColorScheme

internal object TextFieldColorTokens {
    private val Neutral900 = Color(0xFF0F172A)
    private val Neutral700 = Color(0xFF475467)
    private val Neutral500 = Color(0xFF98A2B3)
    private val Neutral300 = Color(0xFFD0D5DD)
    private val Neutral200 = Color(0xFFE4E7EC)
    private val Neutral100 = Color(0xFFF2F4F7)
    private val Error500 = Color(0xFFDC2626)
    private val Primary500 = Color(0xFF2563EB)
    private val Primary100 = Color(0xFFDDE6FF)
    private val Transparent = Color(0x00000000)

    val Filled = AppTextFieldColorScheme(
        textColor = Neutral900,
        placeholderColor = Neutral500,
        labelColor = Neutral700,
        helperColor = Neutral500,
        errorColor = Error500,
        containerColor = Neutral100,
        focusedIndicatorColor = Primary500,
        unfocusedIndicatorColor = Neutral300,
        disabledContainerColor = Neutral200,
        disabledTextColor = Neutral500,
        disabledIndicatorColor = Neutral300,
        cursorColor = Primary500,
        selectionColor = Primary100
    )

    val Outlined = AppTextFieldColorScheme(
        textColor = Neutral900,
        placeholderColor = Neutral500,
        labelColor = Neutral700,
        helperColor = Neutral500,
        errorColor = Error500,
        containerColor = Transparent,
        focusedIndicatorColor = Primary500,
        unfocusedIndicatorColor = Neutral300,
        disabledContainerColor = Transparent,
        disabledTextColor = Neutral500,
        disabledIndicatorColor = Neutral300,
        cursorColor = Primary500,
        selectionColor = Primary100
    )
}
