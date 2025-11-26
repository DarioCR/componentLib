package com.example.componentlib.components.button

import androidx.compose.foundation.BorderStroke
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import com.example.componentlib.components.button.tokens.ButtonColorTokens
import com.example.componentlib.components.button.tokens.ButtonShapeTokens
import com.example.componentlib.components.button.tokens.ButtonSpacingTokens
import com.example.componentlib.components.button.tokens.ButtonTypographyTokens

// Encapsula el conjunto de colores que cada variante de botón puede exponer para distintos estados.
internal data class ButtonColorScheme(
    val containerColor: Color,
    val contentColor: Color,
    val borderColor: Color,
    val disabledContainerColor: Color,
    val disabledContentColor: Color,
    val disabledBorderColor: Color,
    val loadingIndicatorColor: Color,
    val loadingTrackColor: Color
) 

// Información de color para botones solo ícono (estándar o con toggle).
internal data class IconButtonColorScheme(
    val containerColor: Color,
    val contentColor: Color,
    val disabledContainerColor: Color,
    val disabledContentColor: Color
) 

// Tokens de color para las variantes de FAB que solo necesitan manejar estados habilitado/deshabilitado.
internal data class FabColorScheme(
    val containerColor: Color,
    val contentColor: Color,
    val disabledContainerColor: Color,
    val disabledContentColor: Color
) 

// Wrapper de conveniencia que describe el padding horizontal y vertical.
internal data class ButtonPadding(val horizontal: Dp, val vertical: Dp)

// Punto central que mapea AppButtonVariant a tokens de color/forma/espaciado/tipografía.
internal object ButtonTokens {
    // Devuelve la paleta de colores para estados habilitado/deshabilitado/cargando de la variante dada.
    fun colors(variant: AppButtonVariant): ButtonColorScheme = when (variant) {
        AppButtonVariant.Primary -> ButtonColorTokens.Primary
        AppButtonVariant.Secondary -> ButtonColorTokens.Secondary
        AppButtonVariant.Tonal -> ButtonColorTokens.Tonal
        AppButtonVariant.Outlined -> ButtonColorTokens.Outlined
        AppButtonVariant.Text -> ButtonColorTokens.Text
        AppButtonVariant.Ghost -> ButtonColorTokens.Ghost
        AppButtonVariant.Extended -> ButtonColorTokens.Extended
    }

    // Padding horizontal/vertical usado por las distintas familias de botones.
    fun padding(variant: AppButtonVariant): ButtonPadding = when (variant) {
        AppButtonVariant.Primary -> ButtonSpacingTokens.PrimaryPadding
        AppButtonVariant.Secondary -> ButtonSpacingTokens.SecondaryPadding
        AppButtonVariant.Tonal -> ButtonSpacingTokens.TonalPadding
        AppButtonVariant.Outlined -> ButtonSpacingTokens.OutlinedPadding
        AppButtonVariant.Text -> ButtonSpacingTokens.TextPadding
        AppButtonVariant.Ghost -> ButtonSpacingTokens.GhostPadding
        AppButtonVariant.Extended -> ButtonSpacingTokens.ExtendedPadding
    }

    // Tokens de tipografía asociados a cada variante.
    fun typography(variant: AppButtonVariant): TextStyle = when (variant) {
        AppButtonVariant.Primary -> ButtonTypographyTokens.Primary
        AppButtonVariant.Secondary -> ButtonTypographyTokens.Secondary
        AppButtonVariant.Tonal -> ButtonTypographyTokens.Tonal
        AppButtonVariant.Outlined -> ButtonTypographyTokens.Outlined
        AppButtonVariant.Text -> ButtonTypographyTokens.Text
        AppButtonVariant.Ghost -> ButtonTypographyTokens.Ghost
        AppButtonVariant.Extended -> ButtonTypographyTokens.Extended
    }

    // Tokens de forma asociados a cada variante para mantener el redondeo consistente.
    fun shape(variant: AppButtonVariant): Shape = when (variant) {
        AppButtonVariant.Primary -> ButtonShapeTokens.Primary
        AppButtonVariant.Secondary -> ButtonShapeTokens.Secondary
        AppButtonVariant.Tonal -> ButtonShapeTokens.Tonal
        AppButtonVariant.Outlined -> ButtonShapeTokens.Outlined
        AppButtonVariant.Text -> ButtonShapeTokens.Text
        AppButtonVariant.Ghost -> ButtonShapeTokens.Ghost
        AppButtonVariant.Extended -> ButtonShapeTokens.Extended
    }

    // Altura mínima en dp usada al medir la superficie del botón.
    fun minHeight(variant: AppButtonVariant): Dp = when (variant) {
        AppButtonVariant.Extended -> ButtonSpacingTokens.ExtendedMinHeight
        else -> ButtonSpacingTokens.StandardMinHeight
    }

    // Trazo de borde opcional usado por la variante outlined (null para el resto).
    fun borderStroke(variant: AppButtonVariant, enabled: Boolean): BorderStroke? = when (variant) {
        AppButtonVariant.Outlined -> BorderStroke(
            ButtonSpacingTokens.OutlinedBorderWidth,
            if (enabled) ButtonColorTokens.Outlined.borderColor else ButtonColorTokens.Outlined.disabledBorderColor
        )

        else -> null
    }

    val iconSpacing: Dp = ButtonSpacingTokens.IconSpacing
    val loadingSpacing: Dp = ButtonSpacingTokens.LoadingSpacing
    val loadingIndicatorSize: Dp = ButtonSpacingTokens.LoadingIndicatorSize
    val leadingIconSize: Dp = ButtonSpacingTokens.IconSize
    val trailingIconSize: Dp = ButtonSpacingTokens.TrailingIconSize
    val minWidth: Dp = ButtonSpacingTokens.MinWidth
    val loadingStrokeDivisor: Int = ButtonSpacingTokens.LoadingStrokeDivisor

    const val stateTransitionMillis: Int = ButtonSpacingTokens.StateTransitionMillis

    // Colores usados por los icon buttons regulares.
    fun iconButtonColors(): IconButtonColorScheme = ButtonColorTokens.IconButton

    // Colores para los icon buttons con toggle según su estado checked.
    fun toggleIconButtonColors(checked: Boolean): IconButtonColorScheme =
        if (checked) ButtonColorTokens.ToggleIconButtonOn else ButtonColorTokens.ToggleIconButtonOff

    // Colores para FAB y FAB extendido.
    fun fabColors(expanded: Boolean): FabColorScheme =
        if (expanded) ButtonColorTokens.ExtendedFab else ButtonColorTokens.Fab

    val iconButtonSize: Dp = ButtonSpacingTokens.IconButtonSize
    val iconButtonPadding: Dp = ButtonSpacingTokens.IconButtonPadding
    val iconButtonIconSize: Dp = ButtonSpacingTokens.IconButtonIconSize
    val iconButtonShape: Shape = ButtonShapeTokens.IconButton

    val fabSize: Dp = ButtonSpacingTokens.FabSize
    val fabIconSize: Dp = ButtonSpacingTokens.FabIconSize
    val fabElevation: Dp = ButtonSpacingTokens.FabElevation
    val extendedFabPadding: ButtonPadding = ButtonSpacingTokens.ExtendedFabPadding
    val extendedFabIconSpacing: Dp = ButtonSpacingTokens.ExtendedFabIconSpacing
    val fabShape: Shape = ButtonShapeTokens.Fab
    val extendedFabShape: Shape = ButtonShapeTokens.ExtendedFab
    val fabTypography: TextStyle = ButtonTypographyTokens.Fab
}
