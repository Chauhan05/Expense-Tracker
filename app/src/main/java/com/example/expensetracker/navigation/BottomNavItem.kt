package com.example.expensetracker.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBalance
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.BarChart
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
        Icons.Default.AccountBalance,
        NavigationItem.Transaction.title
    )
    data object Add:BottomNavItem(
        NavigationItem.Add.route,
        Icons.Default.Add,
        NavigationItem.Add.title
    )
    data object FinancialReport:BottomNavItem(
        NavigationItem.FinancialReport.route,
        Icons.Default.BarChart,
        NavigationItem.FinancialReport.title
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
    BottomNavItem.FinancialReport,
    BottomNavItem.Profile
)