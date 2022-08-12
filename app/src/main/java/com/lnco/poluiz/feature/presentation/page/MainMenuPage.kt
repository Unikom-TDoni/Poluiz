package com.lnco.poluiz.feature.presentation.page

import androidx.compose.runtime.Composable
import com.lnco.poluiz.core.PoluizAppState
import com.lnco.poluiz.core.navigation.PageNames
import com.lnco.poluiz.feature.presentation.atomic.template.MainMenuTemplate
import com.lnco.poluiz.feature.presentation.state.holder.rememberMainMenuPageStateHolder
import com.lnco.poluiz.feature.presentation.view_model.UserViewModel
import com.lnco.poluiz.module.api.ApiResult
import com.lnco.poluiz.module.compose.collectWithLifecycle

@Composable
fun MainPage(
    appState: PoluizAppState,
    userViewModel: UserViewModel
) {
    val pageStateHolder = rememberMainMenuPageStateHolder {
        if (it == PageNames.Default) userViewModel.logout()
        else appState.navigateTo(it)
    }

    userViewModel.userLogoutFlow.collectWithLifecycle {
        when (it) {
            is ApiResult.Success -> {
                pageStateHolder.dismissLoading()
                appState.navigateTo(PageNames.Login)
            }
            is ApiResult.Loading -> pageStateHolder.onLoadingCallback()
            is ApiResult.Error -> pageStateHolder.onErrorCallback(it.response.throwable.toString())
        }
    }

    MainMenuTemplate(pageStateHolder)
}