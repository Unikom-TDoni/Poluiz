package com.lnco.poluiz.core.navigation

enum class PageNames(val route: String) {
    Default(String()),
    Home("Home"),
    Quiz("Quiz"),
    Login("Login"),
    Register("Register"),
    Leaderboard("Leaderboard"),
    Congratulation("Congratulation/{totalScore}"),
}