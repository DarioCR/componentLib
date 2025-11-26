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

// Floating action button estándar con manejo de clics con throttling y semántica para TalkBack.
// icon: Ícono mostrado en el centro.
// contentDescription: Descripción para TalkBack (nullable cuando es decorativo).
// onClick: Invocado cuando se presiona el FAB (con throttling para evitar spam).
// modifier: Modifier opcional para layout/estilo.
// enabled: Cuando es false muestra colores deshabilitados e ignora clics.
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
    val throttledOnClick = rememberThrottledClick(enabled = enabled, onClick = onClick)

    Surface(
        onClick = throttledOnClick,
        modifier = modifier
            .size(ButtonTokens.fabSize)
            .buttonSemantics(contentDescription = contentDescription),
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

// Variante de FAB extendido con texto e ícono leading opcional.
// text: Etiqueta mostrada junto al ícono opcional.
// icon: Ícono leading opcional.
// onClick: Invocado cuando se presiona el FAB (con throttling para evitar spam).
// modifier: Modifier opcional para layout/estilo.
// enabled: Cuando es false muestra colores deshabilitados e ignora clics.
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
    val throttledOnClick = rememberThrottledClick(enabled = enabled, onClick = onClick)

    Surface(
        onClick = throttledOnClick,
        modifier = modifier
            .defaultMinSize(minHeight = ButtonTokens.minHeight(AppButtonVariant.Extended))
            .buttonSemantics(),
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
