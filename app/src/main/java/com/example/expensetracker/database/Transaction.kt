package com.example.expensetracker.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.expensetracker.data.TransactionType

@Entity(tableName = "transaction_table")
data class Transaction(
    @PrimaryKey(autoGenerate = true)
    val id:Int,
    val type: TransactionType,
    val category: String,
    val description: String,
    val amount: Int,
    val time: String
)
