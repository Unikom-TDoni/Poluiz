package com.lnco.poluiz.feature.presentation.atomic.page

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import com.lnco.poluiz.core.PoluizAppState
import com.lnco.poluiz.core.navigation.PageNames
import com.lnco.poluiz.feature.presentation.atomic.organism.LoadingDialog
import com.lnco.poluiz.feature.presentation.atomic.template.LeaderboardTemplate
import com.lnco.poluiz.feature.presentation.state.holder.rememberLeaderboardPageStateHolder
import com.lnco.poluiz.feature.presentation.view_model.LeaderboardViewModel
import com.lnco.poluiz.module.api.ApiResult
import com.lnco.poluiz.module.compose.collectWithLifecycle

@Composable
fun LeaderboardPage(
    appState: PoluizAppState,
    leaderboardViewModel: LeaderboardViewModel
) {
    val pageStateHolder = rememberLeaderboardPageStateHolder {
        appState.navigateTo(PageNames.Home)
    }

    LaunchedEffect(Unit) {
        leaderboardViewModel.fetch()
    }

    leaderboardViewModel.leaderboardFlow.collectWithLifecycle {
        when (it) {
            is ApiResult.Success -> {
                pageStateHolder.dismissLoading()
                pageStateHolder.init(it.response)
            }
            is ApiResult.Loading -> pageStateHolder.onLoadingCallback()
            is ApiResult.Error -> pageStateHolder.onErrorCallback(it.response.throwable.toString())
        }
    }

    LoadingDialog(pageStateHolder)
    LeaderboardTemplate(pageStateHolder)
}