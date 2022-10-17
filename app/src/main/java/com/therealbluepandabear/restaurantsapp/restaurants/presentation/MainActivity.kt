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
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.therealbluepandabear.restaurantsapp.restaurants.presentation.details.RestaurantDetailsScreen
import com.therealbluepandabear.restaurantsapp.restaurants.presentation.list.RestaurantsScreen
import com.therealbluepandabear.restaurantsapp.restaurants.presentation.list.RestaurantsScreenState
import com.therealbluepandabear.restaurantsapp.restaurants.presentation.list.RestaurantsViewModel
import com.therealbluepandabear.restaurantsapp.restaurants.presentation.new.NewScreen
import com.therealbluepandabear.restaurantsapp.restaurants.presentation.new.NewScreenViewModel
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

        NavHost(navController, startDestination = "new") {
            composable(route = "restaurants") {
                val viewModel: RestaurantsViewModel = hiltViewModel()

                RestaurantsScreen(
                    state = viewModel.state.value,
                    onItemClick = { id ->
                        navController.navigate("restaurants/$id")
                    },
                    onFavoriteClick = { id, oldValue ->
                        viewModel.toggleFavorite(id, oldValue)
                    }
                )
            }

            composable(
                route = "restaurants/{restaurant_id}",
                arguments = listOf(navArgument("restaurant_id") {
                    type = NavType.IntType
                })
            ) {
                RestaurantDetailsScreen()
            }

            composable(route = "new") {
                val viewModel: NewScreenViewModel = hiltViewModel()
                val state = viewModel.state.value

                NewScreen(
                    viewModel,
                    state
                ) { width, height ->
                    navController.navigate("draw")
                }
            }
        }
    }
}