package com.lnco.poluiz.core

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.lnco.poluiz.core.navigation.PageNames

@Composable
fun rememberAppState(
    navController: NavHostController = rememberNavController()
) = remember(navController) { PoluizAppState(navController) }

@Stable
class PoluizAppState(
    val navController: NavHostController,
) {
    fun navigateTo(pageNames: PageNames) =
        when (pageNames) {
            PageNames.Home -> {
                navController.navigate(pageNames.route) {
                    popUpTo(0)
                }
            }
            PageNames.Login -> {
                navController.navigate(pageNames.route) {
                    popUpTo(0)
                }
            }
            else -> navController.navigate(pageNames.route)
        }

    fun navigateWithArgumentTo(pageNames: PageNames, argumentValue: String) {
        val destinationWithArgument = pageNames.route.replaceAfter('/', argumentValue)
        navController.navigate(destinationWithArgument)
    }
}