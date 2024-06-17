package org.purchases.best.ui.navigation

sealed class NavigationItem(var route: String, var title: String) {
    data object HomeScreen : NavigationItem("home_screen", "HomeScreen")
    data object CreateListScreen : NavigationItem("create_list_screen", "CreateListScreen")
    data object ListScreen : NavigationItem("list_screen", "ListScreen")
    data object AddItemScreen : NavigationItem("add_item_screen", "AddItemScreen")
}