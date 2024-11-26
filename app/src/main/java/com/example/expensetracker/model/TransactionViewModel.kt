package com.example.expensetracker.model

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.expensetracker.data.TransactionType
import com.example.expensetracker.database.Transaction
import com.example.expensetracker.database.TransactionRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class TransactionViewModel(private val repository: TransactionRepository) : ViewModel() {
    val getAllItems: LiveData<List<Transaction>> = repository.getAllData()
//    val totalIncome = mutableIntStateOf(0)

    val itemList = listOf("Income", "Expense")

    fun insert(data: Transaction) = viewModelScope.launch {
        // io -> used for disk operation and api call input output operations
//        Dispatchers.Default -> iterating large list , doing heavy cpu computation
        withContext(Dispatchers.IO) {
            repository.insertData(data)
        }
    }


    fun calIncomeExpense(value: List<Transaction>) = viewModelScope.launch {
        withContext(Dispatchers.Default){
            var income = 0
            var expense = 0
            value.forEach { transaction ->
                when(transaction.type) {
                   TransactionType.INCOME -> {
                      income += transaction.amount
                   }
                   TransactionType.EXPENSE -> {
                       expense += transaction.amount
                   }
               }
            }
            _income = income
            _expense = expense
        }
    }
    fun delete(id: Int) = viewModelScope.launch {
        withContext(Dispatchers.IO) {
            repository.deleteData(id)
        }
    }

    private var _money by mutableIntStateOf(0)
    val money: Int get() = _money
    fun changeMoney(newMoney: Int) {
        _money = newMoney
    }

    private var _description by mutableStateOf("")
    val description: String get() = _description
    fun changeDescription(newDesc: String) {
        _description = newDesc
    }

    private var _category by mutableStateOf("")
    val category: String get() = _category

    fun changeCategory(newCat: String) {
        _category = newCat
    }

    private var _date by mutableStateOf("")
    val date: String get() = _date

    fun changeDate(newDate: String) {
        _date = newDate
    }

    private var _isDropDownExpanded by mutableStateOf(false)
    val isDropDownExpanded: Boolean
        get() = _isDropDownExpanded

    fun changeDropDownExpandedValue(newValue: Boolean) {
        _isDropDownExpanded = newValue
    }

    fun toggleDropDownExpandedValue() {
        _isDropDownExpanded = !_isDropDownExpanded
    }

    private var _menuItem by mutableStateOf(itemList[0])
    val menuItem: String
        get() = _menuItem

    fun changeMenuItem(newPosition: String) {
        _menuItem = newPosition
    }

    private var _toastMessage by mutableStateOf("")
    val toastMessage: String get() = _toastMessage
    fun changeToastMessage(newToast: String) {
        _toastMessage = newToast
    }

    private var _income by mutableStateOf(0)
    val income: Int get() = _income
//    fun increaseIncome(i: Int) {
//        _income += i
//    }

    private var _expense by mutableStateOf(0)
    val expense: Int get() = _expense
//    fun increaseExpense(i: Int) {
//        _expense += i
//    }

    val netIncome: Int get() = _income - _expense

    fun clearData(){
        _money=0
        _description=""
        _category=""
        _date=""
    }
//    private var _datePickerController by mutableStateOf(false)
//    val datePickerController:Boolean get()=_datePickerController
//
//    fun toggleDatePickerController(){
//        _datePickerController=!_datePickerController
//    }
//
//    fun changeDatePickerController(newValue:Boolean){
//        _datePickerController=newValue
//    }


}