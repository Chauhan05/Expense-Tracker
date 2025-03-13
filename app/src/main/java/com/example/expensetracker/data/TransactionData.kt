package com.example.expensetracker.data

enum class TransactionType(val transactionCode: Int, val type: String) {
    INCOME(0, "Income"), EXPENSE(1, "Expense"), ALL(2, "Transfer");

    companion object {

        fun getTransactionType(type: Int): TransactionType {
            return when (type) {
                0 -> INCOME
                1 -> EXPENSE
                else -> ALL
            }
        }

    }
}

data class Transaction(
    val amount: String,
    val title: String,
    val type: TransactionType
)

val transactions = listOf(
    Transaction("2500", "Salary", TransactionType.INCOME),
    Transaction("1000", "Rent", TransactionType.EXPENSE),
    Transaction("500", "Freelance", TransactionType.INCOME),
    Transaction("1000", "Rent", TransactionType.EXPENSE),
    Transaction("2500", "Salary", TransactionType.INCOME),
    Transaction("1500", "Groceries", TransactionType.EXPENSE),
    Transaction("500", "Freelance", TransactionType.INCOME),
    Transaction("1500", "Groceries", TransactionType.EXPENSE),
    // Add more transactions as needed
)