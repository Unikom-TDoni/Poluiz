package com.lnco.poluiz.feature.presentation.page

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import com.lnco.poluiz.core.PoluizAppState
import com.lnco.poluiz.core.navigation.PageNames
import com.lnco.poluiz.feature.domain.leaderboard.UpdateScoreRequest
import com.lnco.poluiz.feature.presentation.atomic.organism.LoadingDialog
import com.lnco.poluiz.feature.presentation.atomic.template.QuizTemplate
import com.lnco.poluiz.feature.presentation.state.holder.rememberQuizPageStateHolder
import com.lnco.poluiz.feature.presentation.view_model.LeaderboardViewModel
import com.lnco.poluiz.feature.presentation.view_model.QuizViewModel
import com.lnco.poluiz.feature.presentation.view_model.UserViewModel
import com.lnco.poluiz.module.api.ApiResult
import com.lnco.poluiz.module.compose.collectWithLifecycle

@Composable
fun QuizPage(
    appState: PoluizAppState,
    userViewModel: UserViewModel,
    leaderboardViewModel: LeaderboardViewModel,
    quizViewModel: QuizViewModel = hiltViewModel(),
) {
    val pageStateHolder = rememberQuizPageStateHolder(
        onBackNavigationClick = { appState.navigateTo(PageNames.Home) },
        onLifeRunsOut = {
            if (leaderboardViewModel.currentUserHighScore < it) {
                userViewModel.getCurrentUser()?.let { user ->
                    leaderboardViewModel.update(
                        user.uid,
                        UpdateScoreRequest(
                            user.displayName,
                            user.photoUrl,
                            score = it
                        )
                    )
                }
            } else appState.navigateWithArgumentTo(PageNames.Congratulation, it.toString())
        }
    )

    LaunchedEffect(Unit) {
        quizViewModel.fetch()
    }

    quizViewModel.quizFlow.collectWithLifecycle {
        when (it) {
            is ApiResult.Success -> {
                pageStateHolder.dismissLoading()
                pageStateHolder.init(it.response)
            }
            is ApiResult.Loading -> pageStateHolder.onLoadingCallback()
            is ApiResult.Error -> pageStateHolder.onErrorCallback(it.response.throwable.toString())
        }
    }

    leaderboardViewModel.updateFlow.collectWithLifecycle {
        when (it) {
            is ApiResult.Success -> {
                pageStateHolder.dismissLoading()
                appState.navigateWithArgumentTo(PageNames.Congratulation, it.response.toString())
            }
            is ApiResult.Loading -> pageStateHolder.onLoadingCallback()
            is ApiResult.Error -> pageStateHolder.onErrorCallback(it.response.throwable.toString())
        }
    }

    QuizTemplate(pageStateHolder)
    LoadingDialog(pageStateHolder)
}