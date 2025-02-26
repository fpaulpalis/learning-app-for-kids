package com.ite393.learningappforkids.ui

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.ite393.learningappforkids.data.animals

@Composable
fun AnimalApp() {
    val navController = rememberNavController()
    NavHost(navController, startDestination = "main") {
        composable("main") { AnimalGridScreen(navController) }
        composable("fullScreen/{animalName}") { backStackEntry ->
            val animalName = backStackEntry.arguments?.getString("animalName") ?: ""
            val animal = animals.find { it.name == animalName }
            if (animal != null) {
                FullScreenAnimalView(animal, navController)
            }
        }
    }
}
