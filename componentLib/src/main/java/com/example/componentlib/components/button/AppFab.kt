package com.example.componentlib.components.button

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.ProvideTextStyle
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.semantics.Role

@Composable
fun AppFab(
    icon: ImageVector,
    contentDescription: String?,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true
) {
    val colors = ButtonTokens.fabColors(expanded = false)
    val containerColor = if (enabled) colors.containerColor else colors.disabledContainerColor
    val contentColor = if (enabled) colors.contentColor else colors.disabledContentColor

    Surface(
        onClick = onClick,
        modifier = modifier.size(ButtonTokens.fabSize),
        enabled = enabled,
        shape = ButtonTokens.fabShape,
        color = containerColor,
        contentColor = contentColor,
        tonalElevation = ButtonTokens.fabElevation,
        shadowElevation = ButtonTokens.fabElevation
    ) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Icon(
                imageVector = icon,
                contentDescription = contentDescription,
                modifier = Modifier.size(ButtonTokens.fabIconSize)
            )
        }
    }
}

@Composable
fun AppExtendedFab(
    text: String,
    icon: ImageVector?,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true
) {
    val colors = ButtonTokens.fabColors(expanded = true)
    val containerColor = if (enabled) colors.containerColor else colors.disabledContainerColor
    val contentColor = if (enabled) colors.contentColor else colors.disabledContentColor

    Surface(
        onClick = onClick,
        modifier = modifier
            .defaultMinSize(minHeight = ButtonTokens.minHeight(AppButtonVariant.Extended)),
        enabled = enabled,
        shape = ButtonTokens.extendedFabShape,
        color = containerColor,
        contentColor = contentColor,
        tonalElevation = ButtonTokens.fabElevation,
        shadowElevation = ButtonTokens.fabElevation
    ) {
        ProvideTextStyle(value = ButtonTokens.fabTypography) {
            Row(
                modifier = Modifier
                    .defaultMinSize(minHeight = ButtonTokens.minHeight(AppButtonVariant.Extended))
                    .padding(
                        horizontal = ButtonTokens.extendedFabPadding.horizontal,
                        vertical = ButtonTokens.extendedFabPadding.vertical
                    ),
                horizontalArrangement = Arrangement.spacedBy(ButtonTokens.extendedFabIconSpacing),
                verticalAlignment = Alignment.CenterVertically
            ) {
                if (icon != null) {
                    Icon(
                        imageVector = icon,
                        contentDescription = null,
                        modifier = Modifier.size(ButtonTokens.fabIconSize)
                    )
                }
                Text(text = text)
            }
        }
    }
}
