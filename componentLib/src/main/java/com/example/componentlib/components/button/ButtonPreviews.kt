package com.example.componentlib.components.button

// Composables de preview que muestran variantes de botones para inspección en tiempo de diseño.
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material.icons.rounded.MoreVert
import androidx.compose.material.icons.rounded.Pause
import androidx.compose.material.icons.rounded.PlayArrow
import androidx.compose.material.icons.rounded.Refresh
import androidx.compose.material.icons.rounded.Send
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp

@Preview(name = "Primary Filled", showBackground = true)
@Composable
private fun PrimaryFilledPreview() {
    ButtonPreviewSlot(AppButtonVariant.Primary) {
        AppButton(text = "Primary", onClick = {})
    }
}

@Preview(name = "Primary Icon", showBackground = true)
@Composable
private fun PrimaryIconPreview() {
    ButtonPreviewSlot(AppButtonVariant.Primary) {
        AppButton(
            text = "Enviar",
            onClick = {},
            leadingIcon = Icons.Rounded.Send
        )
    }
}

@Preview(name = "Primary Loading", showBackground = true)
@Composable
private fun PrimaryLoadingPreview() {
    ButtonPreviewSlot(AppButtonVariant.Primary) {
        AppButton(
            text = "Procesando",
            onClick = {},
            loading = true
        )
    }
}

@Preview(name = "Secondary Filled", showBackground = true)
@Composable
private fun SecondaryFilledPreview() {
    ButtonPreviewSlot(AppButtonVariant.Secondary) {
        AppButton(
            text = "Secondary",
            onClick = {},
            variant = AppButtonVariant.Secondary
        )
    }
}

@Preview(name = "Secondary Tonal", showBackground = true)
@Composable
private fun SecondaryTonalPreview() {
    ButtonPreviewSlot(AppButtonVariant.Tonal) {
        AppButton(
            text = "Tonal",
            onClick = {},
            variant = AppButtonVariant.Tonal,
            leadingIcon = Icons.Rounded.Refresh
        )
    }
}

@Preview(name = "Secondary Outlined", showBackground = true)
@Composable
private fun SecondaryOutlinedPreview() {
    ButtonPreviewSlot(AppButtonVariant.Outlined) {
        AppButton(
            text = "Outlined",
            onClick = {},
            variant = AppButtonVariant.Outlined
        )
    }
}

@Preview(name = "Text Button", showBackground = true)
@Composable
private fun TextButtonPreview() {
    ButtonPreviewSlot(AppButtonVariant.Text) {
        AppButton(
            text = "Acción",
            onClick = {},
            variant = AppButtonVariant.Text
        )
    }
}

@Preview(name = "Ghost Button", showBackground = true)
@Composable
private fun GhostButtonPreview() {
    ButtonPreviewSlot(AppButtonVariant.Ghost) {
        AppButton(
            text = "Ghost",
            onClick = {},
            variant = AppButtonVariant.Ghost
        )
    }
}

@Preview(name = "Extended Button", showBackground = true)
@Composable
private fun ExtendedButtonPreview() {
    ButtonPreviewSlot(AppButtonVariant.Extended) {
        AppButton(
            text = "Extended",
            onClick = {},
            variant = AppButtonVariant.Extended,
            leadingIcon = Icons.Rounded.PlayArrow,
            trailingIcon = Icons.Rounded.MoreVert
        )
    }
}

@Preview(name = "Icon Button", showBackground = true)
@Composable
private fun IconButtonPreview() {
    GenericPreviewSlot {
        AppIconButton(
            imageVector = Icons.Rounded.Add,
            contentDescription = "Add",
            onClick = {}
        )
    }
}

@Preview(name = "Toggle Icon Button ON", showBackground = true)
@Composable
private fun ToggleIconButtonOnPreview() {
    GenericPreviewSlot {
        AppToggleIconButton(
            checked = true,
            onCheckedChange = {},
            checkedIcon = Icons.Rounded.Pause,
            uncheckedIcon = Icons.Rounded.PlayArrow,
            contentDescription = "Toggle"
        )
    }
}

@Preview(name = "Toggle Icon Button OFF", showBackground = true)
@Composable
private fun ToggleIconButtonOffPreview() {
    GenericPreviewSlot {
        AppToggleIconButton(
            checked = false,
            onCheckedChange = {},
            checkedIcon = Icons.Rounded.Pause,
            uncheckedIcon = Icons.Rounded.PlayArrow,
            contentDescription = "Toggle"
        )
    }
}

@Preview(name = "FAB", showBackground = true)
@Composable
private fun FabPreview() {
    GenericPreviewSlot {
        AppFab(
            icon = Icons.Rounded.Add,
            contentDescription = "Fab",
            onClick = {}
        )
    }
}

@Preview(name = "Extended FAB", showBackground = true)
@Composable
private fun ExtendedFabPreview() {
    GenericPreviewSlot {
        AppExtendedFab(
            text = "Compose",
            icon = Icons.Rounded.Add,
            onClick = {}
        )
    }
}

@Preview(name = "Dynamic Button", showBackground = true)
@Composable
private fun DynamicButtonPreview() {
    ButtonPreviewSlot(AppButtonVariant.Primary) {
        AppButton(
            text = "Enviar",
            onClick = {},
            leadingIcon = Icons.Rounded.Send
        )
        AppButton(
            text = "Detener",
            onClick = {},
            leadingIcon = Icons.Rounded.Close
        )
    }
}

// Slot de ayuda que distribuye las previews de botones según el padding de cada variante,
// haciendo más legibles las capturas en el panel de previews.
@Composable
private fun ButtonPreviewSlot(
    variant: AppButtonVariant,
    content: @Composable ColumnScope.() -> Unit
) {
    val padding = ButtonTokens.padding(variant)
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = padding.horizontal, vertical = padding.vertical),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(ButtonTokens.iconSpacing),
        content = content
    )
}

// Contenedor reutilizable para previews que no dependen de una variante específica de botón.
@Composable
private fun GenericPreviewSlot(
    padding: Dp = ButtonTokens.iconSpacing,
    content: @Composable ColumnScope.() -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(padding),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(ButtonTokens.iconSpacing),
        content = content
    )
}
