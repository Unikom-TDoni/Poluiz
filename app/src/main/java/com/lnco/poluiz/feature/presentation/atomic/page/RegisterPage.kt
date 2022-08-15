package com.lnco.poluiz.feature.presentation.atomic.page

import androidx.compose.runtime.Composable
import com.lnco.poluiz.core.PoluizAppState
import com.lnco.poluiz.core.navigation.PageNames
import com.lnco.poluiz.feature.presentation.atomic.organism.LoadingDialog
import com.lnco.poluiz.feature.presentation.atomic.template.RegisterTemplate
import com.lnco.poluiz.feature.presentation.state.holder.rememberRegisterPageStateHolder
import com.lnco.poluiz.feature.presentation.view_model.UserViewModel
import com.lnco.poluiz.module.api.ApiResult
import com.lnco.poluiz.module.compose.collectWithLifecycle

@Composable
fun RegisterPage(
    appState: PoluizAppState,
    viewModel: UserViewModel,
) {
    val pageStateHolder = rememberRegisterPageStateHolder(
        onTextNavigationClick = {
            appState.navigateTo(PageNames.valueOf(it))
        },
        onEmailPasswordRegisterClick = {
            viewModel.registerWithEmailPassword(it)
        })

    viewModel.userFlow.collectWithLifecycle {
        when (it) {
            is ApiResult.Success -> {
                pageStateHolder.dismissLoading()
                appState.navigateTo(PageNames.Login)
            }
            is ApiResult.Loading -> pageStateHolder.onLoadingCallback()
            is ApiResult.Error -> pageStateHolder.onErrorCallback(it.response.throwable.toString())
        }
    }

    LoadingDialog(pageStateHolder)
    RegisterTemplate(pageStateHolder)
}