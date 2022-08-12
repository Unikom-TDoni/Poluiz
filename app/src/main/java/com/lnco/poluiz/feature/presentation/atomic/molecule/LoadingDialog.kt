package com.lnco.poluiz.feature.presentation.atomic.organism

import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.lnco.poluiz.module.compose.PageStateHolder

@Composable
fun LoadingDialog(stateHolder: PageStateHolder) {
    if (!stateHolder.isLoading) return
    Dialog(
        onDismissRequest = { stateHolder.dismissLoading() },
        DialogProperties(dismissOnBackPress = false, dismissOnClickOutside = false)
    ) {
        if (stateHolder.shouldShowLoadingProgressBar)
            CircularProgressIndicator()
    }
}