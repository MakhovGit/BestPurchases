package org.purchases.navigation.ui.navigation

import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import org.purchases.add_item_screen.ui.add_item_screen.AddItemScreen
import org.purchases.core.settings.LIST_ID
import org.purchases.core.utils.ZERO
import org.purchases.create_list_screen.ui.create_list_screen.CreateListScreen
import org.purchases.home_screen.ui.home_screen.HomeScreen
import org.purchases.list_screen.ui.list_screen.ListScreen

@SuppressLint("RestrictedApi")
@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = NavigationItem.HomeScreen.route
    ) {
        composable(route = NavigationItem.HomeScreen.route) {
            HomeScreen(
                navigateToListScreen = { bundle ->
                    val destination = navController.findDestination(NavigationItem.ListScreen.route)
                    destination?.let { destinationNotNull ->
                        navController.navigate(destinationNotNull.id, bundle)
                    }
                },
                navigateToCreateListScreen = {
                    val destination =
                        navController.findDestination(NavigationItem.CreateListScreen.route)
                    destination?.let { destinationNotNull ->
                        navController.navigate(destinationNotNull.id)
                    }
                }
            )
        }
        composable(route = NavigationItem.CreateListScreen.route) {
            CreateListScreen(
                navController = navController
            )
        }
        composable(route = NavigationItem.ListScreen.route) { backStackEntry ->
            val listId: Long = backStackEntry.arguments?.getLong(LIST_ID) ?: Long.ZERO
            ListScreen(
                navController = navController,
                listId = listId,
                navigateToAddItemScreen = { bundle ->
                    val destination =
                        navController.findDestination(NavigationItem.AddItemScreen.route)
                    destination?.let { destinationNotNull ->
                        navController.navigate(destinationNotNull.id, bundle)
                    }
                }
            )
        }
        composable(route = NavigationItem.AddItemScreen.route) { backStackEntry ->
            val listId: Long = backStackEntry.arguments?.getLong(LIST_ID) ?: Long.ZERO
            AddItemScreen(
                listId = listId,
                navController = navController,
            )
        }
    }
}
