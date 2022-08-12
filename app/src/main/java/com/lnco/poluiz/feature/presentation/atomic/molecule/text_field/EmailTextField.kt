package com.lnco.poluiz.feature.presentation.atomic.molecule.text_field

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import com.lnco.poluiz.feature.presentation.state.element.molecule.text_field.TextFieldState
import com.lnco.poluiz.feature.presentation.theme.dimens

@Composable
fun ColumnScope.EmailTextField(
    state: TextFieldState
) {
    val focusManager = LocalFocusManager.current
    OutlinedTextField(
        value = state.text,
        singleLine = true,
        label = { Text(state.label) },
        modifier = Modifier.fillMaxWidth(),
        onValueChange = { state.onValueChange(it) },
        keyboardActions = KeyboardActions(onNext = { focusManager.moveFocus(FocusDirection.Down) }),
        keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Next,
            keyboardType = KeyboardType.Email
        )
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