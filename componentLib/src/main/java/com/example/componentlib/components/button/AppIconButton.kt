package com.example.componentlib.components.button

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.toggleableState
import androidx.compose.ui.state.ToggleableState

/**
 * Icon only button that exposes TalkBack semantics and throttled click handling.
 *
 * @param imageVector Icon rendered in the center.
 * @param contentDescription Description announced by TalkBack (nullable when decorative).
 * @param onClick Invoked when the surface is tapped (throttled internally).
 * @param modifier Optional modifier applied to the surface.
 * @param enabled When false renders disabled colors and ignores clicks.
 */
@Composable
fun AppIconButton(
    imageVector: ImageVector,
    contentDescription: String?,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true
) {
    val colors = ButtonTokens.iconButtonColors()
    val containerColor = if (enabled) colors.containerColor else colors.disabledContainerColor
    val contentColor = if (enabled) colors.contentColor else colors.disabledContentColor
    val throttledOnClick = rememberThrottledClick(enabled = enabled, onClick = onClick)

    Surface(
        onClick = throttledOnClick,
        modifier = modifier
            .size(ButtonTokens.iconButtonSize)
            .buttonSemantics(contentDescription = contentDescription),
        enabled = enabled,
        shape = ButtonTokens.iconButtonShape,
        color = containerColor,
        contentColor = contentColor
    ) {
        IconButtonContent(image = imageVector, contentDescription = contentDescription)
    }
}

/**
 * Toggleable icon button that mirrors [AppIconButton] but exposes checked state to TalkBack.
 *
 * @param checked Current toggle state.
 * @param onCheckedChange Callback invoked when the user toggles the button (throttled).
 * @param checkedIcon Icon displayed for the checked state.
 * @param uncheckedIcon Icon displayed for the unchecked state (defaults to [checkedIcon]).
 * @param contentDescription Description announced by TalkBack describing the action/state.
 * @param modifier Optional modifier applied to the surface.
 * @param enabled When false renders disabled colors and ignores toggles.
 */
@Composable
fun AppToggleIconButton(
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    checkedIcon: ImageVector,
    uncheckedIcon: ImageVector = checkedIcon,
    contentDescription: String?,
    modifier: Modifier = Modifier,
    enabled: Boolean = true
) {
    val colors = ButtonTokens.toggleIconButtonColors(checked)
    val containerColor = if (enabled) colors.containerColor else colors.disabledContainerColor
    val contentColor = if (enabled) colors.contentColor else colors.disabledContentColor
    val throttledOnCheckedChange = rememberThrottledOnCheckedChange(enabled = enabled, onCheckedChange = onCheckedChange)

    val semanticsModifier = modifier
        .size(ButtonTokens.iconButtonSize)
        .buttonSemantics(role = Role.Switch, contentDescription = contentDescription)
        .semantics {
            toggleableState = if (checked) ToggleableState.On else ToggleableState.Off
        }

    Surface(
        checked = checked,
        onCheckedChange = throttledOnCheckedChange,
        modifier = semanticsModifier,
        enabled = enabled,
        shape = ButtonTokens.iconButtonShape,
        color = containerColor,
        contentColor = contentColor
    ) {
        val icon = if (checked) checkedIcon else uncheckedIcon
        IconButtonContent(image = icon, contentDescription = contentDescription)
    }
}

/**
 * Shared icon content that keeps padding and icon sizing consistent across button variants.
 */
@Composable
private fun IconButtonContent(image: ImageVector, contentDescription: String?) {
    Box(
        modifier = Modifier.padding(ButtonTokens.iconButtonPadding),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            imageVector = image,
            contentDescription = contentDescription,
            modifier = Modifier.size(ButtonTokens.iconButtonIconSize)
        )
    }
}
