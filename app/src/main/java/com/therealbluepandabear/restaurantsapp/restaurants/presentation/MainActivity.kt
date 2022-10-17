package com.therealbluepandabear.restaurantsapp.restaurants.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.therealbluepandabear.restaurantsapp.restaurants.presentation.list.NewScreenViewModel
import com.therealbluepandabear.restaurantsapp.restaurants.presentation.list.RestaurantsScreen
import com.therealbluepandabear.restaurantsapp.ui.theme.RestaurantsAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RestaurantsAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.wrapContentSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    RestaurantsApp()
                }
            }
        }
    }

    @Composable
    private fun RestaurantsApp() {
        val navController = rememberNavController()

        NavHost(navController, startDestination = "restaurants") {
            composable(route = "restaurants") {
                val viewModel: NewScreenViewModel = hiltViewModel()

                RestaurantsScreen(
                    state = viewModel.state.value,
                    onItemClick = { id ->
                        navController.navigate("restaurants/$id")
                    },
                    onFavoriteClick = { _, _ ->

                    }
                )
            }
        }
    }
}