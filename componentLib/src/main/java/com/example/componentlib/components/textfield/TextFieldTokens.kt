package com.example.componentlib.components.textfield

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import com.example.componentlib.components.textfield.tokens.TextFieldColorTokens
import com.example.componentlib.components.textfield.tokens.TextFieldShapeTokens
import com.example.componentlib.components.textfield.tokens.TextFieldSpacingTokens
import com.example.componentlib.components.textfield.tokens.TextFieldTypographyTokens

// Tokens de color, tipografía, espaciado y forma usados por las variantes de AppTextField.
internal data class AppTextFieldColorScheme(
    val textColor: Color,
    val placeholderColor: Color,
    val labelColor: Color,
    val helperColor: Color,
    val errorColor: Color,
    val containerColor: Color,
    val focusedIndicatorColor: Color,
    val unfocusedIndicatorColor: Color,
    val disabledContainerColor: Color,
    val disabledTextColor: Color,
    val disabledIndicatorColor: Color,
    val cursorColor: Color,
    val selectionColor: Color
) 

// Punto central para resolver los design tokens de los TextField.
internal object TextFieldTokens {
    fun colors(variant: AppTextFieldVariant): AppTextFieldColorScheme = when (variant) {
        AppTextFieldVariant.Filled -> TextFieldColorTokens.Filled
        AppTextFieldVariant.Outlined -> TextFieldColorTokens.Outlined
    }

    fun labelStyle(): TextStyle = TextFieldTypographyTokens.Label
    fun textStyle(): TextStyle = TextFieldTypographyTokens.Body
    fun placeholderStyle(): TextStyle = TextFieldTypographyTokens.Placeholder
    fun supportingTextStyle(): TextStyle = TextFieldTypographyTokens.Supporting

    fun shape(variant: AppTextFieldVariant): Shape = when (variant) {
        AppTextFieldVariant.Filled -> TextFieldShapeTokens.Filled
        AppTextFieldVariant.Outlined -> TextFieldShapeTokens.Outlined
    }

    fun minHeight(): Dp = TextFieldSpacingTokens.MinHeight
    fun contentPadding(): PaddingValues = TextFieldSpacingTokens.ContentPadding
    fun supportingTextSpacing(): Dp = TextFieldSpacingTokens.SupportingTextSpacing
    fun iconSize(): Dp = TextFieldSpacingTokens.IconSize
}
