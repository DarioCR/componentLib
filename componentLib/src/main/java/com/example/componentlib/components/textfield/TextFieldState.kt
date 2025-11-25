package com.example.componentlib.components.textfield

/**
 * Helper that centralizes supporting text logic (helper/error) so composables can expose
 * consistent semantics and visuals.
 */
data class TextFieldState(
    val helperText: String? = null,
    val errorMessage: String? = null
) {
    val isError: Boolean = !errorMessage.isNullOrBlank()
    val supportingText: String? = errorMessage ?: helperText

    fun semanticsDescription(value: String?, label: String?): String? {
        val parts = buildList {
            if (!label.isNullOrBlank()) add(label)
            if (!value.isNullOrBlank()) add(value)
            if (!supportingText.isNullOrBlank()) add(supportingText)
        }
        return parts.joinToString(separator = ". ").ifEmpty { null }
    }
}
