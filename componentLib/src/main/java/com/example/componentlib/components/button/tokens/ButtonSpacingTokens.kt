package com.example.componentlib.components.button.tokens

import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.componentlib.components.button.ButtonPadding

// Reúne constantes de espaciado, tamaño y medidas requeridas por los componentes de botón.
internal object ButtonSpacingTokens {
    val PrimaryPadding = ButtonPadding(horizontal = 24.dp, vertical = 12.dp)
    val SecondaryPadding = ButtonPadding(horizontal = 22.dp, vertical = 12.dp)
    val TonalPadding = ButtonPadding(horizontal = 22.dp, vertical = 11.dp)
    val OutlinedPadding = ButtonPadding(horizontal = 22.dp, vertical = 11.dp)
    val TextPadding = ButtonPadding(horizontal = 14.dp, vertical = 10.dp)
    val GhostPadding = ButtonPadding(horizontal = 20.dp, vertical = 12.dp)
    val ExtendedPadding = ButtonPadding(horizontal = 28.dp, vertical = 14.dp)

    val IconSpacing: Dp = 8.dp
    val LoadingSpacing: Dp = 8.dp
    val StandardMinHeight: Dp = 48.dp
    val ExtendedMinHeight: Dp = 56.dp
    val MinWidth: Dp = 80.dp
    val IconSize: Dp = 20.dp
    val TrailingIconSize: Dp = 18.dp
    val LoadingIndicatorSize: Dp = 18.dp
    const val LoadingStrokeDivisor: Int = 6

    val OutlinedBorderWidth: Dp = 1.5.dp

    val IconButtonSize: Dp = 48.dp
    val IconButtonPadding: Dp = 12.dp
    val IconButtonIconSize: Dp = 24.dp

    val FabSize: Dp = 56.dp
    val FabIconSize: Dp = 24.dp
    val FabElevation: Dp = 6.dp
    val ExtendedFabPadding = ButtonPadding(horizontal = 24.dp, vertical = 16.dp)
    val ExtendedFabIconSpacing: Dp = 10.dp

    const val StateTransitionMillis: Int = 200
}
