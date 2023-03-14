package com.prashant.composetemplate.screens.splash

import android.os.Handler
import android.os.Looper
import androidx.compose.foundation.Image
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.prashant.composetemplate.navigation.SPLASH
import com.prashant.composetemplate.navigation.Screens

@Composable
fun Splash(navHostController: NavHostController, splashVM: SplashVM = hiltViewModel()) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            imageVector = Icons.Default.Notifications,
            contentDescription = SPLASH,
            modifier = Modifier.size(80.dp),
            colorFilter = ColorFilter.tint(Color.White.takeIf { isSystemInDarkTheme() }
                ?: Color.Black)
        )
        Text(
            text = SPLASH,
            style = MaterialTheme.typography.h3.copy(fontWeight = FontWeight.Bold)
        )
    }

    Handler(Looper.getMainLooper()).postDelayed({
        navHostController.navigate(Screens.Login.route){
            popUpTo(Screens.Splash.route) {
                inclusive = true
            }
            launchSingleTop = true
        }
    }, 500)
}

@Preview
@Composable
fun SplashPV() = Splash(navHostController = rememberNavController())