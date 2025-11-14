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

    Surface(
        onClick = onClick,
        modifier = modifier.size(ButtonTokens.iconButtonSize),
        enabled = enabled,
        shape = ButtonTokens.iconButtonShape,
        color = containerColor,
        contentColor = contentColor
    ) {
        IconButtonContent(image = imageVector, contentDescription = contentDescription)
    }
}

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

    Surface(
        checked = checked,
        onCheckedChange = onCheckedChange,
        modifier = modifier.size(ButtonTokens.iconButtonSize),
        enabled = enabled,
        shape = ButtonTokens.iconButtonShape,
        color = containerColor,
        contentColor = contentColor
    ) {
        val icon = if (checked) checkedIcon else uncheckedIcon
        IconButtonContent(image = icon, contentDescription = contentDescription)
    }
}

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
