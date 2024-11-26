package com.example.expensetracker.data

enum class TransactionType {
    INCOME, EXPENSE
}
data class Transaction(
    val amount: String,
    val title: String,
    val type: TransactionType
)
val transactions = listOf(
    Transaction( "2500", "Salary", TransactionType.INCOME),
    Transaction( "1000", "Rent", TransactionType.EXPENSE),
    Transaction( "500", "Freelance", TransactionType.INCOME),
    Transaction( "1000", "Rent", TransactionType.EXPENSE),
    Transaction( "2500", "Salary", TransactionType.INCOME),
    Transaction( "1500", "Groceries", TransactionType.EXPENSE),
    Transaction( "500", "Freelance", TransactionType.INCOME),
    Transaction( "1500", "Groceries", TransactionType.EXPENSE),
    // Add more transactions as needed
)