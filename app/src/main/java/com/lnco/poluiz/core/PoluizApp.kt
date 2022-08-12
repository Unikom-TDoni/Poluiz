package com.lnco.poluiz.core

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.facebook.CallbackManager
import com.lnco.poluiz.core.navigation.PageNames
import com.lnco.poluiz.feature.presentation.page.*
import com.lnco.poluiz.feature.presentation.view_model.LeaderboardViewModel
import com.lnco.poluiz.feature.presentation.view_model.UserViewModel

@Composable
fun PoluizApp(
    facebookCallbackManager: CallbackManager,
    appState: PoluizAppState = rememberAppState(),
    userViewModel: UserViewModel = hiltViewModel(),
    leaderboardViewModel: LeaderboardViewModel = hiltViewModel()
) {
    NavHost(
        navController = appState.navController,
        startDestination = PageNames.Login.route
    ) {
        composable(PageNames.Login.route) {
            userViewModel.getCurrentUser()?.let {
                leaderboardViewModel.fetchUserHighScore(it.uid)
                MainPage(appState, userViewModel)
            } ?: CompositionLocalProvider(
                LocalFacebookCallbackManager provides facebookCallbackManager
            ) { LoginPage(appState, userViewModel) }
        }

        composable(PageNames.Register.route) {
            RegisterPage(appState, userViewModel)
        }

        composable(PageNames.Home.route) {
            MainPage(appState, userViewModel)
        }

        composable(PageNames.Quiz.route) {
            QuizPage(appState, userViewModel, leaderboardViewModel)
        }

        composable(PageNames.Leaderboard.route) {
            LeaderboardPage(appState, leaderboardViewModel)
        }

        composable(
            PageNames.Congratulation.route,
            arguments = listOf(navArgument("totalScore") { type = NavType.IntType })
        ) {
            CongratulationPage(appState, it.arguments?.getInt("totalScore") ?: 0)
        }

    }
}

