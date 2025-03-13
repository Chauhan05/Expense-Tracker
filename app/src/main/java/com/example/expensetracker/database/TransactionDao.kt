package com.example.expensetracker.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface TransactionDao {
    @Insert
    suspend fun insertData(data: Transaction)

    @Query("delete from transaction_table where id=:id")
    suspend fun deleteData(id: Int)

    @Query("SELECT * FROM transaction_table ORDER BY id DESC")
    fun getAllData(): LiveData<List<Transaction>>

    @Query("SELECT * FROM transaction_table WHERE category = :type")
    fun getAllIncome(type: Int): LiveData<List<Transaction>>

    @Query("SELECT * FROM transaction_table WHERE category = :type")
    fun getAllExpense(type: String = "EXPENSE"): LiveData<List<Transaction>>



}