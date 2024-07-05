package org.purchases.navigation.ui.navigation

sealed class NavigationItem(var route: String) {
    data object HomeScreen : NavigationItem("home_screen")
    data object CreateListScreen : NavigationItem("create_list_screen")
    data object ListScreen : NavigationItem("list_screen")
    data object AddItemScreen : NavigationItem("add_item_screen")
}