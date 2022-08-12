package com.lnco.poluiz.module.compose

import androidx.compose.material.ScaffoldState
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

@Stable
abstract class PageStateHolder(
    val scaffoldState: ScaffoldState,
    coroutineScope: CoroutineScope
) {
    init {
        coroutineScope.launch {
            errorMessage.collect {
                if (it.isNotEmpty()) {
                    scaffoldState.snackbarHostState.showSnackbar(
                        message = it,
                    )
                }
            }
        }
    }

    var isLoading by mutableStateOf(false)

    var shouldShowLoadingProgressBar by mutableStateOf(false)
        private set

    private val errorMessage = MutableStateFlow(String())

    fun dismissLoading() {
        isLoading = false
    }

    fun onErrorCallback(message: String) {
        isLoading = false
        errorMessage.value = message
    }

    fun onLoadingCallback(shouldShowLoadingProgressBar: Boolean = true) {
        isLoading = true
        errorMessage.value = String()
        this.shouldShowLoadingProgressBar = shouldShowLoadingProgressBar
    }
}