package com.example.componentlib.components.textfield

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector

// Punto de entrada de conveniencia para campos de contraseña que expone el estado de visibilidad
// para que el llamador mantenga la propiedad de todas las interacciones (state hoisting).
@Composable
fun AppPasswordTextField(
    value: String,
    onValueChange: (String) -> Unit,
    passwordVisible: Boolean,
    onPasswordVisibilityChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
    label: String? = null,
    placeholder: String? = null,
    helperText: String? = null,
    errorMessage: String? = null,
    enabled: Boolean = true,
    readOnly: Boolean = false,
    singleLine: Boolean = true,
    maxLines: Int = 1,
    leadingIcon: ImageVector? = null,
    variant: AppTextFieldVariant = AppTextFieldVariant.Filled
) {
    AppTextField(
        value = value,
        onValueChange = onValueChange,
        modifier = modifier,
        label = label,
        placeholder = placeholder,
        isPassword = true,
        helperText = helperText,
        errorMessage = errorMessage,
        enabled = enabled,
        readOnly = readOnly,
        singleLine = singleLine,
        maxLines = maxLines,
        leadingIcon = leadingIcon,
        trailingIcon = null,
        variant = variant,
        passwordVisible = passwordVisible,
        onPasswordVisibilityChange = onPasswordVisibilityChange
    )
}
