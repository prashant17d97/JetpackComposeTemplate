package com.prashant.composetemplate.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.prashant.composetemplate.screens.home.Home
import com.prashant.composetemplate.screens.login.Login
import com.prashant.composetemplate.screens.splash.Splash

@Composable
fun NavGraph(navHostController: NavHostController) {
    NavHost(navController = navHostController, startDestination = Screens.Splash.route) {
        composable(Screens.Splash.route) {
            Splash(navHostController = navHostController)
        }
        composable(Screens.Login.route) {
            Login(navHostController = navHostController)
        }
        composable(Screens.Home.route) {
            Home(navHostController = navHostController)
        }
    }
}