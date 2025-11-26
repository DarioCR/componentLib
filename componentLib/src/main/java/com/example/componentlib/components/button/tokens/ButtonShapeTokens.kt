package com.example.componentlib.components.button.tokens

import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp

// Formas que siguen la especificación de diseño para cada variante de botón.
internal object ButtonShapeTokens {
    private val LargeRadius = 16.dp
    private val MediumRadius = 14.dp
    private val ExtraLargeRadius = 24.dp

    val Primary: Shape = RoundedCornerShape(LargeRadius)
    val Secondary: Shape = RoundedCornerShape(LargeRadius)
    val Tonal: Shape = RoundedCornerShape(LargeRadius)
    val Outlined: Shape = RoundedCornerShape(LargeRadius)
    val Text: Shape = RoundedCornerShape(LargeRadius)
    val Ghost: Shape = RoundedCornerShape(LargeRadius)
    val Extended: Shape = RoundedCornerShape(ExtraLargeRadius)
    val IconButton: Shape = RoundedCornerShape(MediumRadius)
    val Fab: Shape = CircleShape
    val ExtendedFab: Shape = RoundedCornerShape(ExtraLargeRadius)
}
