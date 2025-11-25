package com.example.componentlib.components.textfield

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Preview(showBackground = true, name = "Filled")
@Composable
private fun FilledTextFieldPreview() {
    var text by remember { mutableStateOf("John Doe") }
    AppTextField(
        value = text,
        onValueChange = { text = it },
        label = "Name",
        placeholder = "Enter your name",
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    )
}

@Preview(showBackground = true, name = "Filled with Icon")
@Composable
private fun FilledWithIconPreview() {
    var text by remember { mutableStateOf("john@example.com") }
    AppTextField(
        value = text,
        onValueChange = { text = it },
        label = "Email",
        placeholder = "name@domain.com",
        leadingIcon = Icons.Filled.Email,
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    )
}

@Preview(showBackground = true, name = "Outlined")
@Composable
private fun OutlinedTextFieldPreview() {
    var text by remember { mutableStateOf("") }
    AppTextField(
        value = text,
        onValueChange = { text = it },
        label = "Company",
        placeholder = "Enter your company",
        variant = AppTextFieldVariant.Outlined,
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    )
}

@Preview(showBackground = true, name = "Password")
@Composable
private fun PasswordTextFieldPreview() {
    var text by remember { mutableStateOf("topsecret") }
    var visible by remember { mutableStateOf(false) }
    AppPasswordTextField(
        value = text,
        onValueChange = { text = it },
        passwordVisible = visible,
        onPasswordVisibilityChange = { visible = it },
        label = "Password",
        placeholder = "Enter password",
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    )
}

@Preview(showBackground = true, name = "Search")
@Composable
private fun SearchTextFieldPreview() {
    var text by remember { mutableStateOf("") }
    AppSearchTextField(
        value = text,
        onValueChange = { text = it },
        placeholder = "Search anything",
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    )
}

@Preview(showBackground = true, name = "Multiline")
@Composable
private fun MultilineTextFieldPreview() {
    var text by remember { mutableStateOf("Line 1\nLine 2") }
    AppTextField(
        value = text,
        onValueChange = { text = it },
        label = "Description",
        placeholder = "Write your notes",
        singleLine = false,
        maxLines = 4,
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    )
}

@Preview(showBackground = true, name = "Error")
@Composable
private fun ErrorTextFieldPreview() {
    var text by remember { mutableStateOf("invalid-email") }
    AppTextField(
        value = text,
        onValueChange = { text = it },
        label = "Email",
        placeholder = "name@domain.com",
        errorMessage = "Invalid email address",
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    )
}

@Preview(showBackground = true, name = "Helper Text")
@Composable
private fun HelperTextFieldPreview() {
    var text by remember { mutableStateOf("") }
    AppTextField(
        value = text,
        onValueChange = { text = it },
        label = "Username",
        helperText = "Use 6 or more characters",
        leadingIcon = Icons.Filled.Person,
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    )
}

@Preview(showBackground = true, name = "Disabled")
@Composable
private fun DisabledTextFieldPreview() {
    AppTextField(
        value = "Disabled value",
        onValueChange = {},
        label = "Disabled",
        enabled = false,
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    )
}

@Preview(showBackground = true, name = "Read Only")
@Composable
private fun ReadOnlyTextFieldPreview() {
    AppTextField(
        value = "Read-only data",
        onValueChange = {},
        label = "Read only",
        readOnly = true,
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    )
}

@Preview(showBackground = true, name = "Numeric")
@Composable
private fun NumericTextFieldPreview() {
    var text by remember { mutableStateOf("1234") }
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        AppTextField(
            value = text,
            onValueChange = { text = it },
            label = "Account number",
            placeholder = "0000",
            isNumericOnly = true,
            leadingIcon = Icons.Filled.Person
        )
    }
}
