package com.example.expensetracker.model

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import androidx.lifecycle.switchMap
import androidx.lifecycle.viewModelScope
import com.example.expensetracker.data.TransactionType
import com.example.expensetracker.data.enums.SortOrder
import com.example.expensetracker.database.Transaction
import com.example.expensetracker.database.TransactionRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class TransactionViewModel(private val repository: TransactionRepository) : ViewModel() {
    val allItemsLiveData: LiveData<List<Transaction>> = repository.getAllData()

    private val _filteredList: MutableLiveData<List<Transaction>> = MutableLiveData()
    private val _filter: MutableLiveData<String> = MutableLiveData("Transfer")
    private val _sortOrder: MutableLiveData<SortOrder> = MutableLiveData(SortOrder.Newest)
    val currentFilter: LiveData<String> get() = _filter
    val currentSortOrder: LiveData<SortOrder> get() = _sortOrder
    val filterSortMediatorLiveData: MediatorLiveData<List<Transaction>> = MediatorLiveData()
    val filteredList: LiveData<List<Transaction>> = filterSortMediatorLiveData

    init {
        filterSortMediatorLiveData.addSource(allItemsLiveData) {
            applyFiltersAndSort()
        }

        filterSortMediatorLiveData.addSource(currentFilter) { f ->
           applyFiltersAndSort()
        }

        filterSortMediatorLiveData.addSource(currentSortOrder) { s ->
            applyFiltersAndSort()
        }
    }

    fun setFilter(filter: String) {
        _filter.value = filter
//        applyFiltersAndSort()
    }

    fun setSortOrder(sortOrder: SortOrder) {
        _sortOrder.value = sortOrder
//        applyFiltersAndSort()
    }


    fun applyFiltersAndSort(){

        allItemsLiveData.value?.let { originalList ->
            val filter: String = currentFilter.value ?: "Transfer"
            val sortOrder: SortOrder = _sortOrder.value ?: SortOrder.Newest
            // Apply category filter
            val transactionForCategory =
                if (filter == "Transfer") originalList else originalList.filter { transaction ->
                    TransactionType.getTransactionType(transaction.type).type == filter
                }

            // Apply sorting
            val sortedList = when (sortOrder) {
                SortOrder.Lowest -> transactionForCategory.sortedBy { it.amount }
                SortOrder.Highest -> transactionForCategory.sortedByDescending { it.amount }
                SortOrder.Newest -> transactionForCategory
                SortOrder.Oldest -> transactionForCategory.sortedBy {
                    it.id
                }
            }

//            _filteredList.value = sortedList
            filterSortMediatorLiveData.value = sortedList
        }
    }
//    val totalIncome = mutableIntStateOf(0)


    val itemList = listOf("Income", "Expense")

    fun insert(data: Transaction) = viewModelScope.launch {
        // io -> used for disk operation and api call input output operations
//        Dispatchers.Default -> iterating large list , doing heavy cpu computation
        repository.insertData(data)
    }

//    //fun getAllTransactionWithFilter(filter: String="Transfer", sort: SortOrder= SortOrder.DEFAULT) =
//    getAllItems.switchMap
//    {
//        originalList ->
//        val transactionForCategory =
//            if (filter == "Transfer") originalList else originalList.filter { transaction ->
//                transaction.category == filter
//            }
//
//        val sortedList = if (sort == SortOrder.DESCENDING) {
//            transactionForCategory.sortedBy {
//                it.amount
//            }
//        } else if (sort == SortOrder.DEFAULT) transactionForCategory
//        else {
//            transactionForCategory.sortedByDescending {
//                it.amount
//            }
//        }
//
//        _filteredList.value = sortedList
//
//        _filteredList
//    }


    fun calIncomeExpense(value: List<Transaction>) = viewModelScope.launch {
        withContext(Dispatchers.Default) {
            var income = 0
            var expense = 0
            value.forEach { transaction ->
                when (TransactionType.getTransactionType(transaction.type)) {
                    TransactionType.INCOME -> {
                        income += transaction.amount
                    }

                    TransactionType.EXPENSE -> {
                        expense += transaction.amount
                    }

                    TransactionType.ALL -> {

                    }
                }
            }
            _income = income
            _expense = expense
        }
    }

    fun delete(id: Int) = viewModelScope.launch {
        // switch to io thread for network and database operations
        repository.deleteData(id)
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

    private var _showBottomSheet by mutableStateOf(false)
    val showBottomSheet: Boolean get() = _showBottomSheet

    fun toggleBottomSheet() {
        _showBottomSheet = !_showBottomSheet
    }



    val netIncome: Int get() = _income - _expense

    fun clearData() {
        _money = 0
        _description = ""
        _category = ""
        _date = ""
        Log.d("clear", "value ${if (_date == "") "cleared" else "not cleared"}")
    }



}