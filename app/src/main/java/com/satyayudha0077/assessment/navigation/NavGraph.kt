package com.satyayudha0077.assessment.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.satyayudha0077.assessment.R
import com.satyayudha0077.assessment.model.Buah
import com.satyayudha0077.assessment.ui.screen.DetailScreen
import com.satyayudha0077.assessment.ui.screen.MainScreen

@Composable
fun SetupNavGraph(navController: NavHostController) {
    val data = listOf(
        Buah(stringResource(R.string.buah_a), R.drawable.apel, com.satyayudha0077.assessment.R.string.apel),
        Buah(stringResource(R.string.buah_al), R.drawable.alpukat, com.satyayudha0077.assessment.R.string.alpukat),
        Buah(stringResource(R.string.buah_j), R.drawable.jeruk, com.satyayudha0077.assessment.R.string.jeruk),
        Buah(stringResource(R.string.buah_jam), R.drawable.jambu, com.satyayudha0077.assessment.R.string.jambu),
        Buah(stringResource(R.string.buah_p), R.drawable.pisang, com.satyayudha0077.assessment.R.string.pisang)
    )

    NavHost(navController = navController, startDestination = "home") {
        composable("home") {
            MainScreen(navController, data)
        }
        composable("detail/{index}") { backStackEntry ->
            val index = backStackEntry.arguments?.getString("index")?.toIntOrNull() ?: 0
            DetailScreen(buah = data[index], navController = navController)
        }
    }
}
