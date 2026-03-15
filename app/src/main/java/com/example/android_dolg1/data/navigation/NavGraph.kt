package com.example.android_dolg1.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.*
import com.example.android_dolg1.ui.components.CountryListScreen
import com.example.android_dolg1.ui.screens.detail.CountryDetailScreen

import com.example.android_dolg1.ui.screens.favouritesScreen.FavouritesScreen

import com.example.android_dolg1.viewmodel.CountryViewModel

@Composable
fun NavGraph() {
    val navController = rememberNavController()
    val vm: CountryViewModel = hiltViewModel()

    NavHost(navController, startDestination = "list") {
        composable("list") {
            CountryListScreen(navController, vm)
        }
        composable("detail/{code}") { backStackEntry ->
            val code = backStackEntry.arguments?.getString("code") ?: ""
            CountryDetailScreen(code, vm) { navController.popBackStack() }
        }
        composable("Favourites") {
            FavouritesScreen(vm, onCountryClick = { code ->
                navController.navigate("detail/$code")
            }, onBack = { navController.popBackStack() })
        }
    }
}