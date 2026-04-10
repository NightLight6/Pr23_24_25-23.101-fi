package com.example.pr23_23101_fi

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    var showSplash by remember { mutableStateOf(true) }

    if (showSplash) {
        SplashScreen(onSplashComplete = { })
    } else {
        NavHost(navController = navController, startDestination = "onboarding") {
            composable("onboarding") {
                OnboardingScreen(
                    onSkip = { navController.navigate("login") },
                    onFinish = { navController.navigate("login") }
                )
            }
            composable("login") {
                LoginScreen(onNavigateToEmailCode = { navController.navigate("email_code") })
            }
            composable("email_code") {
                EmailCodeScreen(onNavigateToCreatePassword = { navController.navigate("create_password") })
            }
            composable("create_password") {
                CreatePasswordScreen(onNavigateToMain = { navController.navigate("dashboard") })
            }
            composable("dashboard") {
                MainDashboardScreen()
            }
        }
    }
}