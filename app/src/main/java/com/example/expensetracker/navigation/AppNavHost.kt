//package com.example.expensetracker.navigation
//
//
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.getValue
//import androidx.compose.runtime.livedata.observeAsState
//import androidx.compose.ui.Modifier
//import androidx.navigation.NavHostController
//import com.example.expensetracker.model.TransactionViewModel
//import com.example.expensetracker.screens.Add
//import com.example.expensetracker.screens.HomeScreen
//import com.example.expensetracker.screens.Profile
//import com.example.expensetracker.screens.Statistics
//import com.example.expensetracker.screens.Transaction
//import com.google.accompanist.navigation.animation.AnimatedNavHost
//import com.google.accompanist.navigation.animation.composable
//
//
//@Composable
//fun AppNavHost(
//    navController: NavHostController,
//    modifier: Modifier = Modifier,
//    transactionViewModel: TransactionViewModel
//) {
//    val transactions by transactionViewModel.getAllItems.observeAsState(initial = emptyList())
//
//    AnimatedNavHost(navController = navController, startDestination = NavigationItem.Home.route) {
//        composable(NavigationItem.Home.route) {
//            HomeScreen(navController, modifier, transactionViewModel)
//        }
//        composable(NavigationItem.Transaction.route) {
//            Transaction(navController, transactions, modifier)
//        }
//        composable(NavigationItem.Add.route) {
//            Add(navController, modifier, transactionViewModel)
//        }
//        composable(NavigationItem.Statistics.route) {
//            Statistics(navController, modifier, transactionViewModel)
//        }
//        composable(NavigationItem.Profile.route) {
//            Profile(navController, modifier)
//        }
//    }
//}

//package com.example.expensetracker.navigation
//
//import androidx.compose.animation.*
//import androidx.compose.animation.core.tween
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.getValue
//import androidx.compose.runtime.livedata.observeAsState
//import androidx.compose.ui.Modifier
//import androidx.navigation.NavHostController
//import com.example.expensetracker.model.TransactionViewModel
//import com.example.expensetracker.screens.*
//import com.google.accompanist.navigation.animation.AnimatedNavHost
//import com.google.accompanist.navigation.animation.composable

//@OptIn(ExperimentalAnimationApi::class)
//@Composable
//fun AppNavHost(
//    navController: NavHostController,
//    modifier: Modifier = Modifier,
//    transactionViewModel: TransactionViewModel
//) {
//    val transactions by transactionViewModel.getAllItems.observeAsState(initial = emptyList())
//
//    AnimatedNavHost(
//        navController = navController,
//        startDestination = NavigationItem.Home.route
//    ) {
//        composable(
//            route = NavigationItem.Home.route,
//            enterTransition = { slideInHorizontally({ it }, tween(300)) }, // Slide from right
//            exitTransition = { slideOutHorizontally({ -it }, tween(300)) } // Slide to left
//        ) {
//            HomeScreen(navController, modifier, transactionViewModel)
//        }
//        composable(
//            route = NavigationItem.Transaction.route,
//            enterTransition = { slideInHorizontally({ it }, tween(300)) }, // Slide from right
//            exitTransition = { slideOutHorizontally({ -it }, tween(300)) } // Slide to left
//        ) {
//            Transaction(navController, transactions, modifier)
//        }
//        composable(
//            route = NavigationItem.Add.route,
//            enterTransition = { slideInHorizontally({ it }, tween(300)) }, // Slide from right
//            exitTransition = { slideOutHorizontally({ -it }, tween(300)) } // Slide to left
//        ) {
//            Add(navController, modifier, transactionViewModel)
//        }
//        composable(
//            route = NavigationItem.Statistics.route,
//            enterTransition = { slideInHorizontally({ it }, tween(300)) }, // Slide from right
//            exitTransition = { slideOutHorizontally({ -it }, tween(300)) } // Slide to left
//        ) {
//            Statistics(navController, modifier, transactionViewModel)
//        }
//        composable(
//            route = NavigationItem.Profile.route,
//            enterTransition = { slideInHorizontally({ it }, tween(300)) }, // Slide from right
//            exitTransition = { slideOutHorizontally({ -it }, tween(300)) } // Slide to left
//        ) {
//            Profile(navController, modifier)
//        }
//    }
//}


//package com.example.expensetracker.navigation
//
//import androidx.compose.animation.*
//import androidx.compose.animation.core.tween
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.getValue
//import androidx.compose.runtime.livedata.observeAsState
//import androidx.compose.ui.Modifier
//import androidx.navigation.NavHostController
//import com.example.expensetracker.model.TransactionViewModel
//import com.example.expensetracker.screens.*
//import com.google.accompanist.navigation.animation.AnimatedNavHost
//import com.google.accompanist.navigation.animation.composable
//
//@OptIn(ExperimentalAnimationApi::class)
//@Composable
//fun AppNavHost(
//    navController: NavHostController,
//    modifier: Modifier = Modifier,
//    transactionViewModel: TransactionViewModel
//) {
//    val transactions by transactionViewModel.getAllItems.observeAsState(initial = emptyList())
//    val time=1200
//    AnimatedNavHost(
//        navController = navController,
//        startDestination = NavigationItem.Home.route
//    ) {
//        composable(
//            route = NavigationItem.Home.route,
//            enterTransition = {
//                slideIntoContainer(
//                    AnimatedContentTransitionScope.SlideDirection.Right,
//                    animationSpec = tween(time) // Smooth transition
//                )
//            },
//            exitTransition = {
//                slideOutOfContainer(
//                    AnimatedContentTransitionScope.SlideDirection.Left,
//                    animationSpec = tween(time)
//                )
//            }
//        ) {
//            HomeScreen(navController, modifier, transactionViewModel)
//        }
//        composable(
//            route = NavigationItem.Transaction.route,
//            enterTransition = {
//                slideIntoContainer(
//                    AnimatedContentTransitionScope.SlideDirection.Right,
//                    animationSpec = tween(time)
//                )
//            },
//            exitTransition = {
//                slideOutOfContainer(
//                    AnimatedContentTransitionScope.SlideDirection.Left,
//                    animationSpec = tween(time)
//                )
//            }
//        ) {
//            Transaction(navController, transactions, modifier)
//        }
//        composable(
//            route = NavigationItem.Add.route,
//            enterTransition = {
//                slideIntoContainer(
//                    AnimatedContentTransitionScope.SlideDirection.Right,
//                    animationSpec = tween(time)
//                )
//            },
//            exitTransition = {
//                slideOutOfContainer(
//                    AnimatedContentTransitionScope.SlideDirection.Left,
//                    animationSpec = tween(time)
//                )
//            }
//        ) {
//            Add(navController, modifier, transactionViewModel)
//        }
//        composable(
//            route = NavigationItem.Statistics.route,
//            enterTransition = {
//                slideIntoContainer(
//                    AnimatedContentTransitionScope.SlideDirection.Right,
//                    animationSpec = tween(time)
//                )
//            },
//            exitTransition = {
//                slideOutOfContainer(
//                    AnimatedContentTransitionScope.SlideDirection.Left,
//                    animationSpec = tween(time)
//                )
//            }
//        ) {
//            Statistics(navController, modifier, transactionViewModel)
//        }
//        composable(
//            route = NavigationItem.Profile.route,
//            enterTransition = {
//                slideIntoContainer(
//                    AnimatedContentTransitionScope.SlideDirection.Right,
//                    animationSpec = tween(time)
//                )
//            },
//            exitTransition = {
//                slideOutOfContainer(
//                    AnimatedContentTransitionScope.SlideDirection.Left,
//                    animationSpec = tween(time)
//                )
//            }
//        ) {
//            Profile(navController, modifier)
//        }
//    }
//}


//@OptIn(ExperimentalAnimationApi::class)
//@Composable
//fun AppNavHost(
//    navController: NavHostController,
//    modifier: Modifier = Modifier,
//    transactionViewModel: TransactionViewModel
//) {
//    val transactions by transactionViewModel.getAllItems.observeAsState(initial = emptyList())
//
//    AnimatedNavHost(
//        navController = navController,
//        startDestination = NavigationItem.Home.route
//    ) {
//        composable(
//            route = NavigationItem.Home.route,
//            enterTransition = { fadeIn(animationSpec = tween(500)) },
//            exitTransition = { fadeOut(animationSpec = tween(500)) }
//        ) {
//            HomeScreen(navController, modifier, transactionViewModel)
//        }
//        composable(
//            route = NavigationItem.Transaction.route,
//            enterTransition = { fadeIn(animationSpec = tween(500)) },
//            exitTransition = { fadeOut(animationSpec = tween(500)) }
//        ) {
//            Transaction(navController, transactions, modifier)
//        }
//        composable(
//            route = NavigationItem.Add.route,
//            enterTransition = { fadeIn(animationSpec = tween(500)) },
//            exitTransition = { fadeOut(animationSpec = tween(500)) }
//        ) {
//            Add(navController, modifier, transactionViewModel)
//        }
//        composable(
//            route = NavigationItem.Statistics.route,
//            enterTransition = { fadeIn(animationSpec = tween(500)) },
//            exitTransition = { fadeOut(animationSpec = tween(500)) }
//        ) {
//            Statistics(navController, modifier, transactionViewModel)
//        }
//        composable(
//            route = NavigationItem.Profile.route,
//            enterTransition = { fadeIn(animationSpec = tween(500)) },
//            exitTransition = { fadeOut(animationSpec = tween(500)) }
//        ) {
//            Profile(navController, modifier)
//        }
//    }
//}


import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.example.expensetracker.model.TransactionViewModel
import com.example.expensetracker.navigation.NavigationItem
import com.example.expensetracker.screens.Add
import com.example.expensetracker.screens.FinancialReport
import com.example.expensetracker.screens.HomeScreen
import com.example.expensetracker.screens.Profile
import com.example.expensetracker.screens.Transaction
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun AppNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    transactionViewModel: TransactionViewModel
) {

//    val transactions by transactionViewModel.getAllTransactionWithFilter(
//        "Transfer",
//        SortOrder.DEFAULT
//    ).observeAsState(initial = emptyList())

    AnimatedNavHost(
        navController = navController,
        startDestination = NavigationItem.Home.route
    ) {
        composable(
            route = NavigationItem.Home.route,
            enterTransition = { fadeIn() },
            exitTransition = { fadeOut() }
        ) {
            HomeScreen(navController, modifier, transactionViewModel)
        }
        composable(
            route = NavigationItem.Transaction.route,
            enterTransition = { fadeIn() },
            exitTransition = { fadeOut() }
        ) {
            Transaction(
                navController, transactionViewModel, modifier, transactionViewModel.showBottomSheet,
            ) { transactionViewModel.toggleBottomSheet() }
        }
        composable(
            route = NavigationItem.Add.route,
            enterTransition = { fadeIn() },
            exitTransition = { fadeOut() }
        ) {
            Add(navController, modifier, transactionViewModel)
        }
        composable(
            route = NavigationItem.FinancialReport.route,
            enterTransition = { fadeIn() },
            exitTransition = { fadeOut() }
        ) {
            FinancialReport(navController, modifier, transactionViewModel)
        }
        composable(
            route = NavigationItem.Profile.route,
            enterTransition = { fadeIn() },
            exitTransition = { fadeOut() }
        ) {
            Profile(navController, modifier)
        }
    }
}

