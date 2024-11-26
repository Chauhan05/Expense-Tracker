package com.example.expensetracker

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.compose.rememberNavController
import com.example.expensetracker.database.TransactionDatabase
import com.example.expensetracker.database.TransactionRepository
import com.example.expensetracker.database.TransactionViewModelFactory
import com.example.expensetracker.model.TransactionViewModel
import com.example.expensetracker.navigation.AppNavHost
import com.example.expensetracker.ui.theme.ExpenseTrackerTheme
import com.example.expensetracker.ui_components.BottomNavigationBar

class MainActivity : ComponentActivity() {
    private lateinit var transactionViewModel: TransactionViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        transactionViewModel = ViewModelProvider(
            this,
            TransactionViewModelFactory(
                TransactionRepository(
                    TransactionDatabase.getDatabase(this).transactionDao()
                )
            )
        )[TransactionViewModel::class.java]

        transactionViewModel.getAllItems.observe(this) {
            transactionViewModel.calIncomeExpense(it ?: listOf())
        }

        setContent {
//            demotransactionList.forEach { item ->
//                transactionViewModel.insert(item)
//            }
            ExpenseTrackerTheme {
                val navController = rememberNavController()
                Scaffold(modifier = Modifier.fillMaxSize(),
                    bottomBar = { BottomNavigationBar(navController) }
                ) { innerPadding ->
                    AppNavHost(navController, Modifier.padding(innerPadding), transactionViewModel)
                }
            }
        }
    }
}

//val demotransactionList: List<Transaction> = listOf(
//    Transaction(
//        0,
//        TransactionType.INCOME,
//        "Salary",
//        "Salary of  September",
//        5000,
//        "10:00 AM"
//    )
//)

