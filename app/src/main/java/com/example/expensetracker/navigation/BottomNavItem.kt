package com.example.expensetracker.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Circle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.MonetizationOn
import androidx.compose.material.icons.filled.Person
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomNavItem(
    val route: String,
    val icon: ImageVector,
    val label: String
) {

    data object Home:BottomNavItem(
        NavigationItem.Home.route,
        Icons.Default.Home,
        NavigationItem.Home.title
    )
    data object Transaction:BottomNavItem(
        NavigationItem.Transaction.route,
        Icons.Default.MonetizationOn,
        NavigationItem.Transaction.title
    )
    data object Add:BottomNavItem(
        NavigationItem.Add.route,
        Icons.Default.Add,
        NavigationItem.Add.title
    )
    data object Statistics:BottomNavItem(
        NavigationItem.Statistics.route,
        Icons.Default.Circle,
        NavigationItem.Statistics.title
    )
    data object Profile:BottomNavItem(
        NavigationItem.Profile.route,
        Icons.Default.Person,
        NavigationItem.Profile.title
    )
}
val bottomNavItems = listOf(
    BottomNavItem.Home,
    BottomNavItem.Transaction,
    BottomNavItem.Add,
    BottomNavItem.Statistics,
    BottomNavItem.Profile
)