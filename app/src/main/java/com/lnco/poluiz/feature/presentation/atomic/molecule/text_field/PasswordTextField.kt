package com.lnco.poluiz.feature.presentation.atomic.molecule.text_field

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import com.lnco.poluiz.feature.presentation.state.element.molecule.text_field.PasswordTextFieldState
import com.lnco.poluiz.feature.presentation.theme.dimens

@Composable
fun ColumnScope.PasswordTextField(
    state: PasswordTextFieldState
) {
    val focusManager = LocalFocusManager.current
    OutlinedTextField(
        value = state.text,
        singleLine = true,
        label = { Text(state.label) },
        modifier = Modifier.fillMaxWidth(),
        onValueChange = { state.onValueChange(it) },
        visualTransformation = if (state.isPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
        keyboardActions = KeyboardActions(onDone = { focusManager.clearFocus() }),
        keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Done,
            keyboardType = KeyboardType.Password
        ),
        trailingIcon = {
            val image = if (state.isPasswordVisible) Icons.Filled.Visibility
            else Icons.Filled.VisibilityOff
            IconButton(onClick = { state.togglePasswordState() }) {
                Icon(image, "icon")
            }
        }
    )
    if (state.isError) {
        Text(
            text = state.errorMessage,
            color = MaterialTheme.colors.error,
            style = MaterialTheme.typography.caption,
            modifier = Modifier.padding(start = MaterialTheme.dimens.normal100)
        )
    }
}