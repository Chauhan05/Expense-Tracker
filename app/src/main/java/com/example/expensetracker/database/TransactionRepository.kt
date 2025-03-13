package com.example.expensetracker.database

import androidx.lifecycle.LiveData
import com.example.expensetracker.data.TransactionType
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class TransactionRepository(private val transactionDao: TransactionDao) {
    suspend fun insertData(data: Transaction) {
        withContext(Dispatchers.IO) {
            transactionDao.insertData(data)
        }
    }

    suspend fun deleteData(id: Int) {
        withContext(Dispatchers.IO) {
            transactionDao.deleteData(id)
        }
    }


    fun getAllData(): LiveData<List<Transaction>> = transactionDao.getAllData()

    fun getAllIncome(): LiveData<List<Transaction>> =
        transactionDao.getAllIncome(TransactionType.INCOME.transactionCode)

    fun getAllExpense(): LiveData<List<Transaction>> = transactionDao.getAllExpense()
}