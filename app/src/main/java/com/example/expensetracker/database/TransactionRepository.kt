package com.example.expensetracker.database

import androidx.lifecycle.LiveData

class TransactionRepository(private val transactionDao: TransactionDao) {
    suspend fun insertData(data: Transaction) {
        transactionDao.insertData(data)
    }

    suspend fun deleteData(id: Int) {
        transactionDao.deleteData(id)
    }


    fun getAllData(): LiveData<List<Transaction>> = transactionDao.getAllData()
}