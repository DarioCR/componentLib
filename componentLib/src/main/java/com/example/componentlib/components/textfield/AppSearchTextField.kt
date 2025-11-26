package com.example.componentlib.components.textfield

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

// Wrapper específico para búsquedas que conecta los íconos correctos y el comportamiento de limpiar.
@Composable
fun AppSearchTextField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    placeholder: String? = null,
    helperText: String? = null,
    enabled: Boolean = true,
    readOnly: Boolean = false,
    singleLine: Boolean = true,
    maxLines: Int = 1,
    variant: AppTextFieldVariant = AppTextFieldVariant.Filled,
    onClear: (() -> Unit)? = null
) {
    val clearAction = onClear ?: { onValueChange("") }
    AppTextField(
        value = value,
        onValueChange = onValueChange,
        modifier = modifier,
        placeholder = placeholder,
        isSearch = true,
        helperText = helperText,
        enabled = enabled,
        readOnly = readOnly,
        singleLine = singleLine,
        maxLines = maxLines,
        variant = variant,
        trailingIcon = null,
        onTrailingIconClick = { clearAction() }
    )
}
