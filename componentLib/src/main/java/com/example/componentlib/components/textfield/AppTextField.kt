package com.example.componentlib.components.textfield

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.selection.LocalTextSelectionColors
import androidx.compose.foundation.text.selection.TextSelectionColors
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.Visibility
import androidx.compose.material.icons.outlined.VisibilityOff
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TextField as Material3TextField
import androidx.compose.material3.OutlinedTextField as Material3OutlinedTextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.semantics.error
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.stateDescription
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

sealed class AppTextFieldVariant {
    object Filled : AppTextFieldVariant()
    object Outlined : AppTextFieldVariant()
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppTextField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    label: String? = null,
    placeholder: String? = null,
    isPassword: Boolean = false,
    isNumericOnly: Boolean = false,
    isSearch: Boolean = false,
    helperText: String? = null,
    errorMessage: String? = null,
    enabled: Boolean = true,
    readOnly: Boolean = false,
    singleLine: Boolean = true,
    maxLines: Int = 1,
    leadingIcon: ImageVector? = null,
    trailingIcon: ImageVector? = null,
    variant: AppTextFieldVariant = AppTextFieldVariant.Filled,
    passwordVisible: Boolean = false,
    onPasswordVisibilityChange: ((Boolean) -> Unit)? = null,
    onTrailingIconClick: (() -> Unit)? = null
) {
    val supportingState = TextFieldState(helperText = helperText, errorMessage = errorMessage)
    val colorScheme = TextFieldTokens.colors(variant)
    val colors = textFieldColors(colorScheme)
    val textStyle = TextFieldTokens.textStyle()
    val labelStyle = TextFieldTokens.labelStyle()
    val placeholderStyle = TextFieldTokens.placeholderStyle()
    val supportingStyle = TextFieldTokens.supportingTextStyle()
    val contentPadding = TextFieldTokens.contentPadding()
    val iconSize = TextFieldTokens.iconSize()
    val minHeight = TextFieldTokens.minHeight()
    val shape = TextFieldTokens.shape(variant)
    val textFieldModifier = Modifier.padding(contentPadding)
    val selectionColors = remember(colorScheme) {
        TextSelectionColors(
            handleColor = colorScheme.cursorColor,
            backgroundColor = colorScheme.selectionColor
        )
    }

    val sanitizedOnValueChange: (String) -> Unit = remember(onValueChange, isNumericOnly) {
        if (!isNumericOnly) onValueChange else { incoming ->
            val filtered = incoming.filter { it.isDigit() }
            onValueChange(filtered)
        }
    }

    val internalVisibilityState = if (isPassword && onPasswordVisibilityChange == null) {
        rememberSaveable { mutableStateOf(passwordVisible) }
    } else {
        null
    }
    val resolvedPasswordVisible = when {
        !isPassword -> false
        onPasswordVisibilityChange != null -> passwordVisible
        else -> internalVisibilityState?.value ?: false
    }

    val togglePasswordVisibility: (() -> Unit)? = when {
        !isPassword -> null
        onPasswordVisibilityChange != null -> {
            { onPasswordVisibilityChange(!passwordVisible) }
        }
        else -> {
            { internalVisibilityState?.let { it.value = !it.value } }
        }
    }

    val trailingIconConfig = when {
        isPassword -> TrailingIconConfig(
            icon = if (resolvedPasswordVisible) Icons.Outlined.VisibilityOff else Icons.Outlined.Visibility,
            contentDescription = if (resolvedPasswordVisible) "Hide password" else "Show password",
            onClick = togglePasswordVisibility
        )

        isSearch && value.isNotEmpty() -> TrailingIconConfig(
            icon = Icons.Filled.Clear,
            contentDescription = "Clear search",
            onClick = onTrailingIconClick ?: { sanitizedOnValueChange("") }
        )

        trailingIcon != null -> TrailingIconConfig(
            icon = trailingIcon,
            contentDescription = null,
            onClick = onTrailingIconClick
        )

        else -> null
    }

    val resolvedLeadingIcon = leadingIcon ?: if (isSearch) Icons.Filled.Search else null

    val semanticsDescription = supportingState.semanticsDescription(value = value, label = label)

    val baseModifier = modifier
        .defaultMinSize(minHeight = minHeight)
        .semantics {
            if (!semanticsDescription.isNullOrBlank()) {
                stateDescription = semanticsDescription
            }
            if (supportingState.isError && !errorMessage.isNullOrBlank()) {
                error(errorMessage)
            }
        }

    val visualTransformation = if (isPassword && !resolvedPasswordVisible) {
        PasswordVisualTransformation()
    } else {
        VisualTransformation.None
    }

    val keyboardOptions = when {
        isNumericOnly -> KeyboardOptions(keyboardType = KeyboardType.Number)
        isPassword -> KeyboardOptions(keyboardType = KeyboardType.Password)
        else -> KeyboardOptions.Default
    }

    CompositionLocalProvider(LocalTextSelectionColors provides selectionColors) {
        Column(modifier = baseModifier) {
            when (variant) {
                AppTextFieldVariant.Filled -> {
                    Material3TextField(
                        modifier = textFieldModifier,
                        value = value,
                        onValueChange = sanitizedOnValueChange,
                        enabled = enabled,
                        readOnly = readOnly,
                        singleLine = singleLine,
                        maxLines = maxLines,
                        label = label?.let { { FieldLabel(it, labelStyle) } },
                        placeholder = placeholder?.let { { FieldPlaceholder(it, placeholderStyle) } },
                        leadingIcon = resolvedLeadingIcon?.let { { FieldIcon(it, iconSize) } },
                        trailingIcon = trailingIconConfig?.let { { TrailingIcon(it, iconSize) } },
                        textStyle = textStyle,
                        visualTransformation = visualTransformation,
                        colors = colors,
                        keyboardOptions = keyboardOptions,
                        isError = supportingState.isError,
                        shape = shape
                    )
                }

                AppTextFieldVariant.Outlined -> {
                    Material3OutlinedTextField(
                        modifier = textFieldModifier,
                        value = value,
                        onValueChange = sanitizedOnValueChange,
                        enabled = enabled,
                        readOnly = readOnly,
                        singleLine = singleLine,
                        maxLines = maxLines,
                        label = label?.let { { FieldLabel(it, labelStyle) } },
                        placeholder = placeholder?.let { { FieldPlaceholder(it, placeholderStyle) } },
                        leadingIcon = resolvedLeadingIcon?.let { { FieldIcon(it, iconSize) } },
                        trailingIcon = trailingIconConfig?.let { { TrailingIcon(it, iconSize) } },
                        textStyle = textStyle,
                        visualTransformation = visualTransformation,
                        colors = colors,
                        keyboardOptions = keyboardOptions,
                        isError = supportingState.isError,
                        shape = shape
                    )
                }
            }

            supportingState.supportingText?.let {
                Spacer(modifier = Modifier.height(TextFieldTokens.supportingTextSpacing()))
                SupportingText(
                    text = it,
                    isError = supportingState.isError,
                    enabled = enabled && !readOnly,
                    colorScheme = colorScheme,
                    style = supportingStyle
                )
            }
        }
    }
}

@Composable
private fun FieldLabel(text: String, style: TextStyle) {
    Text(text = text, style = style)
}

@Composable
private fun FieldPlaceholder(text: String, style: TextStyle) {
    Text(text = text, style = style)
}

@Composable
private fun FieldIcon(imageVector: ImageVector, size: Dp) {
    Icon(
        imageVector = imageVector,
        contentDescription = null,
        modifier = Modifier.size(size)
    )
}

@Composable
private fun TrailingIcon(config: TrailingIconConfig, size: Dp) {
    val iconContent: @Composable () -> Unit = {
        Icon(
            imageVector = config.icon,
            contentDescription = config.contentDescription,
            modifier = Modifier.size(size)
        )
    }
    if (config.onClick != null) {
        IconButton(onClick = config.onClick) {
            iconContent()
        }
    } else {
        iconContent()
    }
}

@Composable
private fun SupportingText(
    text: String,
    isError: Boolean,
    enabled: Boolean,
    colorScheme: AppTextFieldColorScheme,
    style: TextStyle
) {
    val color = when {
        isError -> colorScheme.errorColor
        !enabled -> colorScheme.disabledTextColor
        else -> colorScheme.helperColor
    }
    Text(text = text, style = style, color = color, modifier = Modifier.padding(horizontal = 4.dp))
}

private data class TrailingIconConfig(
    val icon: ImageVector,
    val contentDescription: String?,
    val onClick: (() -> Unit)?
)

@Composable
private fun textFieldColors(colorScheme: AppTextFieldColorScheme): TextFieldColors {
    return TextFieldDefaults.colors(
        focusedTextColor = colorScheme.textColor,
        unfocusedTextColor = colorScheme.textColor,
        disabledTextColor = colorScheme.disabledTextColor,
        cursorColor = colorScheme.cursorColor,
        errorCursorColor = colorScheme.errorColor,
        focusedPlaceholderColor = colorScheme.placeholderColor,
        unfocusedPlaceholderColor = colorScheme.placeholderColor,
        focusedContainerColor = colorScheme.containerColor,
        unfocusedContainerColor = colorScheme.containerColor,
        disabledContainerColor = colorScheme.disabledContainerColor,
        errorContainerColor = colorScheme.containerColor,
        focusedIndicatorColor = colorScheme.focusedIndicatorColor,
        unfocusedIndicatorColor = colorScheme.unfocusedIndicatorColor,
        disabledIndicatorColor = colorScheme.disabledIndicatorColor,
        errorIndicatorColor = colorScheme.errorColor
    )
}
