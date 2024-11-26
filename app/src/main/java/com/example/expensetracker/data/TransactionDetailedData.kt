package com.example.expensetracker.data

data class TransactionDetailedData(
    val type: TransactionType,
    val category: String,
    val description: String,
    val amount: Int,
    val time: String
)

val transactionList = listOf(
    TransactionDetailedData(
        TransactionType.INCOME,
        "Salary",
        "Salary of  September",
        5000,
        "10:00 AM"
    ),
    TransactionDetailedData(
        TransactionType.EXPENSE,
        "Shopping",
        "Grocery Shopping",
        300,
        "6:00 PM"
    ),
    TransactionDetailedData(
        TransactionType.INCOME,
        "Freelancing",
        "Fiver",
        3000,
        "11:00 AM"
    ),
    TransactionDetailedData(
        TransactionType.EXPENSE,
        "Recharge",
        "Phone Recharge",
        499,
        "3:00 PM"
    ),
    TransactionDetailedData(
        TransactionType.INCOME,
        "Salary",
        "Salary of  September",
        5000,
        "10:00 AM"
    ),
    TransactionDetailedData(
        TransactionType.EXPENSE,
        "Rent",
        "Paid Rent",
        1300,
        "4:00 PM"
    ),
    TransactionDetailedData(
        TransactionType.INCOME,
        "Salary",
        "Salary of  September",
        5000,
        "10:00 AM"
    ),
    TransactionDetailedData(TransactionType.EXPENSE, "Shopping", "Grocery Shopping", 300, "6:00 PM")
)