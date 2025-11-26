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

// Botón solo ícono que expone semántica para TalkBack y maneja clics con throttling.
// imageVector: Ícono renderizado en el centro.
// contentDescription: Descripción anunciada por TalkBack (nullable cuando es decorativo).
// onClick: Invocado cuando se toca la superficie (con throttling interno).
// modifier: Modifier opcional aplicado a la superficie.
// enabled: Cuando es false muestra colores deshabilitados e ignora clics.
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
        // La descripción ya está en la superficie, el ícono interno no necesita contentDescription.
        IconButtonContent(image = imageVector)
    }
}

// Botón de ícono con toggle que replica AppIconButton pero expone el estado checked a TalkBack.
// checked: Estado actual del toggle.
// onCheckedChange: Callback invocado cuando el usuario cambia el estado (con throttling).
// checkedIcon: Ícono mostrado cuando el estado es checked.
// uncheckedIcon: Ícono mostrado cuando el estado es unchecked (por defecto checkedIcon).
// contentDescription: Descripción anunciada por TalkBack que describe la acción/estado.
// modifier: Modifier opcional aplicado a la superficie.
// enabled: Cuando es false muestra colores deshabilitados e ignora cambios de estado.
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
        // La descripción ya está en la superficie, el ícono interno no necesita contentDescription.
        IconButtonContent(image = icon)
    }
}

// Contenido compartido de ícono que mantiene padding y tamaño de ícono consistentes entre variantes.
@Composable
private fun IconButtonContent(image: ImageVector) {
    Box(
        modifier = Modifier.padding(ButtonTokens.iconButtonPadding),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            imageVector = image,
            contentDescription = null,
            modifier = Modifier.size(ButtonTokens.iconButtonIconSize)
        )
    }
}
