package com.example.componentlib.components.button.tokens

import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

// Definiciones de tipografía que respetan la jerarquía visual de cada tipo de botón.
internal object ButtonTypographyTokens {
    private val BaseFamily = FontFamily.SansSerif

    val Primary = TextStyle(
        fontFamily = BaseFamily,
        fontWeight = FontWeight.SemiBold,
        fontSize = 16.sp,
        lineHeight = 20.sp,
        letterSpacing = 0.5.sp
    )

    val Secondary = TextStyle(
        fontFamily = BaseFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 16.sp,
        lineHeight = 20.sp,
        letterSpacing = 0.5.sp
    )

    val Tonal = TextStyle(
        fontFamily = BaseFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 15.sp,
        lineHeight = 20.sp,
        letterSpacing = 0.4.sp
    )

    val Outlined = TextStyle(
        fontFamily = BaseFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 15.sp,
        lineHeight = 20.sp,
        letterSpacing = 0.4.sp
    )

    val Text = TextStyle(
        fontFamily = BaseFamily,
        fontWeight = FontWeight.SemiBold,
        fontSize = 15.sp,
        lineHeight = 20.sp,
        letterSpacing = 0.5.sp
    )

    val Ghost = TextStyle(
        fontFamily = BaseFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 15.sp,
        lineHeight = 20.sp,
        letterSpacing = 0.3.sp
    )

    val Extended = TextStyle(
        fontFamily = BaseFamily,
        fontWeight = FontWeight.SemiBold,
        fontSize = 17.sp,
        lineHeight = 22.sp,
        letterSpacing = 0.4.sp
    )

    val Fab = TextStyle(
        fontFamily = BaseFamily,
        fontWeight = FontWeight.SemiBold,
        fontSize = 16.sp,
        lineHeight = 20.sp,
        letterSpacing = 0.4.sp
    )
}
