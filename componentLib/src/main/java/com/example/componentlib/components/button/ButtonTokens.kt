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

/**
 * Encapsulates the set of colors that a button variant can expose for different states.
 */
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

/**
 * Color information for icon-only buttons (standard or toggleable).
 */
internal data class IconButtonColorScheme(
    val containerColor: Color,
    val contentColor: Color,
    val disabledContainerColor: Color,
    val disabledContentColor: Color
)

/**
 * Color tokens for FAB variants which only require enabled/disabled state handling.
 */
internal data class FabColorScheme(
    val containerColor: Color,
    val contentColor: Color,
    val disabledContainerColor: Color,
    val disabledContentColor: Color
)

/**
 * Convenience wrapper describing horizontal and vertical padding pairs.
 */
internal data class ButtonPadding(val horizontal: Dp, val vertical: Dp)

/**
 * Central place that maps [AppButtonVariant] values to color/shape/spacing/typography tokens.
 */
internal object ButtonTokens {
    /**
     * Returns the enabled/disabled/loading color palette for the provided variant.
     */
    fun colors(variant: AppButtonVariant): ButtonColorScheme = when (variant) {
        AppButtonVariant.Primary -> ButtonColorTokens.Primary
        AppButtonVariant.Secondary -> ButtonColorTokens.Secondary
        AppButtonVariant.Tonal -> ButtonColorTokens.Tonal
        AppButtonVariant.Outlined -> ButtonColorTokens.Outlined
        AppButtonVariant.Text -> ButtonColorTokens.Text
        AppButtonVariant.Ghost -> ButtonColorTokens.Ghost
        AppButtonVariant.Extended -> ButtonColorTokens.Extended
    }

    /**
     * Horizontal/vertical padding used by the various button families.
     */
    fun padding(variant: AppButtonVariant): ButtonPadding = when (variant) {
        AppButtonVariant.Primary -> ButtonSpacingTokens.PrimaryPadding
        AppButtonVariant.Secondary -> ButtonSpacingTokens.SecondaryPadding
        AppButtonVariant.Tonal -> ButtonSpacingTokens.TonalPadding
        AppButtonVariant.Outlined -> ButtonSpacingTokens.OutlinedPadding
        AppButtonVariant.Text -> ButtonSpacingTokens.TextPadding
        AppButtonVariant.Ghost -> ButtonSpacingTokens.GhostPadding
        AppButtonVariant.Extended -> ButtonSpacingTokens.ExtendedPadding
    }

    /**
     * Typography tokens bound to each variant.
     */
    fun typography(variant: AppButtonVariant): TextStyle = when (variant) {
        AppButtonVariant.Primary -> ButtonTypographyTokens.Primary
        AppButtonVariant.Secondary -> ButtonTypographyTokens.Secondary
        AppButtonVariant.Tonal -> ButtonTypographyTokens.Tonal
        AppButtonVariant.Outlined -> ButtonTypographyTokens.Outlined
        AppButtonVariant.Text -> ButtonTypographyTokens.Text
        AppButtonVariant.Ghost -> ButtonTypographyTokens.Ghost
        AppButtonVariant.Extended -> ButtonTypographyTokens.Extended
    }

    /**
     * Shape tokens bound to each variant for consistent rounding.
     */
    fun shape(variant: AppButtonVariant): Shape = when (variant) {
        AppButtonVariant.Primary -> ButtonShapeTokens.Primary
        AppButtonVariant.Secondary -> ButtonShapeTokens.Secondary
        AppButtonVariant.Tonal -> ButtonShapeTokens.Tonal
        AppButtonVariant.Outlined -> ButtonShapeTokens.Outlined
        AppButtonVariant.Text -> ButtonShapeTokens.Text
        AppButtonVariant.Ghost -> ButtonShapeTokens.Ghost
        AppButtonVariant.Extended -> ButtonShapeTokens.Extended
    }

    /**
     * Minimum height in dp used when measuring a button surface.
     */
    fun minHeight(variant: AppButtonVariant): Dp = when (variant) {
        AppButtonVariant.Extended -> ButtonSpacingTokens.ExtendedMinHeight
        else -> ButtonSpacingTokens.StandardMinHeight
    }

    /**
     * Optional border stroke used by outlined variants (null for the rest).
     */
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

    /**
     * Colors used by regular icon buttons.
     */
    fun iconButtonColors(): IconButtonColorScheme = ButtonColorTokens.IconButton

    /**
     * Colors for toggle icon buttons depending on their checked state.
     */
    fun toggleIconButtonColors(checked: Boolean): IconButtonColorScheme =
        if (checked) ButtonColorTokens.ToggleIconButtonOn else ButtonColorTokens.ToggleIconButtonOff

    /**
     * Colors for FAB vs extended FAB.
     */
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
