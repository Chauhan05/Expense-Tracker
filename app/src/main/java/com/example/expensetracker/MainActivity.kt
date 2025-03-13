package com.example.expensetracker

import AppNavHost
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.expensetracker.database.TransactionDatabase
import com.example.expensetracker.database.TransactionRepository
import com.example.expensetracker.database.TransactionViewModelFactory
import com.example.expensetracker.model.TransactionViewModel
import com.example.expensetracker.ui.theme.ExpenseTrackerTheme
import com.example.expensetracker.ui_components.BottomNavigationBar
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState

class MainActivity : ComponentActivity() {
//    private lateinit var transactionViewModel: TransactionViewModel

    @OptIn(ExperimentalAnimationApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

//        transactionViewModel = ViewModelProvider(
//            this,
//            TransactionViewModelFactory(
//                TransactionRepository(
//                    TransactionDatabase.getDatabase(this).transactionDao()
//                )
//            )
//        )[TransactionViewModel::class.java]
//
//        transactionViewModel.getAllItems.observe(this) {
//            transactionViewModel.calIncomeExpense(it ?: listOf())
//        }

        setContent {
            val transactionViewModel: TransactionViewModel = viewModel(
                factory = TransactionViewModelFactory(
                    TransactionRepository(
                        TransactionDatabase.getDatabase(this).transactionDao()
                    )
                )
            )
//            val transactionViewModel: TransactionViewModel= viewModel()
            val allItems by transactionViewModel.allItemsLiveData.observeAsState(initial = emptyList())

            LaunchedEffect(allItems) {
                transactionViewModel.calIncomeExpense(allItems)
            }
//            val transactionViewModel= viewModel<TransactionViewModel>()
            ExpenseTrackerTheme {
//                val navController = rememberNavController()
                val navController = rememberAnimatedNavController()
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    bottomBar = { BottomNavigationBar(navController) }
                ) { innerPadding ->
                    AppNavHost(navController, Modifier.padding(innerPadding), transactionViewModel)
                }
            }
        }
    }
}



