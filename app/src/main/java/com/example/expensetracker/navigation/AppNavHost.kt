package com.example.expensetracker.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.expensetracker.model.TransactionViewModel
import com.example.expensetracker.screens.Add
import com.example.expensetracker.screens.HomeScreen
import com.example.expensetracker.screens.Profile
import com.example.expensetracker.screens.Statistics
import com.example.expensetracker.screens.Transaction

@Composable
fun AppNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    transactionViewModel: TransactionViewModel
) {
    val transactions by transactionViewModel.getAllItems.observeAsState(initial = emptyList())

    NavHost(navController = navController, startDestination = NavigationItem.Home.route) {
        composable(NavigationItem.Home.route) {
            HomeScreen(navController, modifier, transactionViewModel)
        }
        composable(NavigationItem.Transaction.route) {
            Transaction(navController, transactions, modifier)
        }
        composable(NavigationItem.Add.route) {
            Add(navController, modifier, transactionViewModel)
        }
        composable(NavigationItem.Statistics.route) {
            Statistics(navController, modifier, transactionViewModel)
        }
        composable(NavigationItem.Profile.route) {
            Profile(navController, modifier)
        }
    }
}