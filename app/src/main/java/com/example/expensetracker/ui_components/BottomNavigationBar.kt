package com.example.expensetracker.ui_components

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.expensetracker.navigation.Screen
import com.example.expensetracker.navigation.bottomNavItems

@Composable
fun BottomNavigationBar(navController: NavController, modifier: Modifier = Modifier) {
    NavigationBar {
        val backStackEntry = navController.currentBackStackEntryAsState()
        bottomNavItems.forEach { item ->
            NavigationBarItem(
                selected = backStackEntry.value?.destination?.route == item.route,
                onClick = {
                    if (backStackEntry.value?.destination?.route != item.route) {  // Prevent reloading the same screen
                        navController.navigate(item.route) {
                            popUpTo(navController.graph.startDestinationId) { inclusive = false }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                },
                icon = {
                    Icon(
                        imageVector = item.icon,
                        contentDescription = ""
                    )
                },
                label = {
                    Text(item.label)
                }
            )
        }
    }
}