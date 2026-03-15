package com.example.android_dolg1.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.*
import com.example.android_dolg1.ui.screens.detail.CountryDetailScreen
import com.example.android_dolg1.ui.screens.list.CountryListScreen

@Composable
fun NavGraph() {

    val navController = rememberNavController()

    NavHost(navController, startDestination = "list") {

        composable("list") {
            CountryListScreen(navController)
        }

        composable("detail/{code}") { backStack ->

            val code = backStack.arguments?.getString("code") ?: ""

            CountryDetailScreen(code)
        }

    }
}