package org.purchases.best.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import org.koin.androidx.compose.koinViewModel
import org.purchases.best.ui.screens.add_item_screen.AddItemScreen
import org.purchases.best.ui.screens.add_item_screen.AddItemScreenViewModel
import org.purchases.best.ui.screens.create_list_screen.CreateListScreen
import org.purchases.best.ui.screens.create_list_screen.CreateListScreenViewModel
import org.purchases.best.ui.screens.home_screen.HomeScreen
import org.purchases.best.ui.screens.home_screen.HomeScreenViewModel
import org.purchases.best.ui.screens.list_screen.ListScreen
import org.purchases.best.ui.screens.list_screen.ListScreenViewModel
import org.purchases.core.settings.LIST_ID
import org.purchases.core.utils.ZERO

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = NavigationItem.HomeScreen.route
    ) {
        composable(route = NavigationItem.HomeScreen.route) {
            HomeScreen(
                viewModel = koinViewModel<HomeScreenViewModel>(),
                navController = navController
            )
        }
        composable(route = NavigationItem.CreateListScreen.route) {
            CreateListScreen(
                viewModel = koinViewModel<CreateListScreenViewModel>(),
                navController = navController
            )
        }
        composable(route = NavigationItem.ListScreen.route) { backStackEntry ->
            val listId: Long = backStackEntry.arguments?.getLong(LIST_ID) ?: Long.ZERO
            ListScreen(
                viewModel = koinViewModel<ListScreenViewModel>(),
                navController = navController,
                listId = listId
            )
        }
        composable(route = NavigationItem.AddItemScreen.route) { backStackEntry ->
            val listId: Long = backStackEntry.arguments?.getLong(LIST_ID) ?: Long.ZERO
            AddItemScreen(
                listId = listId,
                viewModel = koinViewModel<AddItemScreenViewModel>(),
                navController = navController,
            )
        }
    }
}
