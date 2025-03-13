package com.example.expensetracker.screens

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.FilterList
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.expensetracker.data.TransactionType
import com.example.expensetracker.data.enums.SortOrder
import com.example.expensetracker.database.Transaction
import com.example.expensetracker.model.TransactionViewModel
import com.example.expensetracker.navigation.NavigationItem
import com.google.accompanist.flowlayout.FlowRow


const val TAG = "TransactionScreen"

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Transaction(
    navController: NavController,
    transactionViewModel: TransactionViewModel,
    modifier: Modifier = Modifier,
    showBottomSheet: Boolean,
    toggleBottomSheet: () -> Unit,

    ) {


    val allTransactionsState = transactionViewModel.allItemsLiveData.observeAsState()

    val allTransactions = allTransactionsState.value ?: listOf()

    Log.d("Transaction at start", "${allTransactions.size}")
    val currentFilter = transactionViewModel.currentFilter.observeAsState()
    val currentSortOrder = transactionViewModel.currentSortOrder.observeAsState()

    val sheetState = rememberModalBottomSheetState()

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color(0xFFFFE0BD).copy(alpha = 0.4f))
    ) {
        DescBarCmp("Transaction", navController, toggleBottomSheet)
        if (showBottomSheet) {
            ModalBottomSheet(
                onDismissRequest = {
                    toggleBottomSheet()
                },
                sheetState = sheetState
            ) {
                FilterTransactionUI(
                    currentFilter.value ?: "Transfer",
                    currentSortOrder.value ?: SortOrder.Newest,
                    changeFilter = { filter ->
                        transactionViewModel.setFilter(filter)
                    },
                    changeSort = { sort ->
                        transactionViewModel.setSortOrder(sort)
                    },
                    onContinueClick = {
//                        transactionViewModel.applyFiltersAndSort()
                        toggleBottomSheet()
                    }
                )
            }
        }

        TransactionList(allTransactions, transactionViewModel)
    }
}

@Composable
fun TransactionList(
    allTransaction: List<Transaction>,
    transactionViewModel: TransactionViewModel,
    modifier: Modifier = Modifier
) {
    val filteredList by transactionViewModel.filteredList.observeAsState()
    Log.d("transaction", "The data of transaction is $allTransaction")
    LazyColumn(
        modifier.fillMaxSize()
    ) {
        items(filteredList ?: allTransaction) { item ->
            TransactionItem(item)
        }
    }
}

@Composable
fun TransactionItem(data: Transaction, modifier: Modifier = Modifier) {
    Log.d(TAG, "TransactionItem: $data")
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .background(Color.White, shape = RoundedCornerShape(8.dp))
            .padding(16.dp)
    ) {
        Column {
            Text(data.category, fontSize = 22.sp, fontWeight = FontWeight.Bold, maxLines = 1)
            Text(
                data.description,
                fontSize = 18.sp,
                color = Color.DarkGray.copy(0.7f),
                modifier = Modifier.padding(top = 4.dp),
                maxLines = 1
            )
        }
        Column(
            horizontalAlignment = Alignment.End
        ) {
            Text(
                if (data.type == TransactionType.INCOME.transactionCode) "+" + data.amount.toString() else "-" + data.amount.toString(),
                color = if (data.type == TransactionType.INCOME.transactionCode) Color.Green else Color.Red,
                fontSize = 22.sp,
                maxLines = 1
            )
            Text(
                data.time,
                color = Color.DarkGray.copy(0.7f),
                modifier = Modifier.padding(top = 4.dp),
                maxLines = 1
            )
        }
    }
}

@Composable
fun DescBarCmp(title: String, navController: NavController, toggleBottomSheet: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(
            onClick = {
                navController.navigate(NavigationItem.Home.route) {
                    popUpTo(navController.graph.startDestinationId) { inclusive = false }
                    launchSingleTop = true
                    restoreState = true
                }
            }
        ) {
            Icon(Icons.Default.ArrowBack, "backButton", modifier = Modifier.size(40.dp))
        }
        Text(
            title,
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.weight(1f),
            textAlign = TextAlign.Center
        )
        //        Spacer(modifier=Modifier.weight(1f))
        IconButton(
            //            open a bottom sheet
            onClick = {
                toggleBottomSheet()
            },
        ) {
            Icon(Icons.Default.FilterList, "sort", modifier = Modifier.size(40.dp))
        }
    }

}

@Composable
fun FilterTransactionUI(
    currentFilter: String,
    currentSortOrder: SortOrder,
    changeFilter: (String) -> Unit,
    changeSort: (SortOrder) -> Unit,
    onContinueClick: () -> Unit
) {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
        ) {
            // Header with Reset button and badge

            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    "Filter Transaction",
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp
                )

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.clickable {
                        changeFilter("Transfer")
                        changeSort(SortOrder.Newest)
//                        selectedFilter = "Transfer"
//                        selectedSort = "Newest"
                    }
                ) {
                    Text(
                        text = "Reset",
                        color = Color(0xFF915AFF),
                        fontWeight = FontWeight.Medium,
                        fontSize = 16.sp,
                        modifier = Modifier.padding(end = 4.dp)
                    )

//                    if (appliedCount > 0) {
//                        Box(
//                            modifier = Modifier
//                                .size(30.dp)
//                                .background(Color.Red, RoundedCornerShape(50))
//                                .border(1.dp, Color.White, RoundedCornerShape(50)),
//                            contentAlignment = Alignment.Center
//                        ) {
//                            Text(
//                                text = appliedCount.toString(),
//                                color = Color.White,
//                                fontSize = 14.sp,
//                                fontWeight = FontWeight.Bold,
//                            )
//                        }
//                    }
                }
            }


            Spacer(modifier = Modifier.height(12.dp))

            // Filter By Section
            Text("Filter By", fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(8.dp))

            FlowRow(
                mainAxisSpacing = 8.dp,
                crossAxisSpacing = 8.dp,
                modifier = Modifier.fillMaxWidth()
            ) {
                listOf("Income", "Expense", "Transfer").forEach { filter ->
                    FilterButton(
                        text = filter,
                        isSelected = currentFilter == filter,
                        onClick = {
                            changeFilter(filter)
//                            selectedFilter = filter
                        }
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Sort By Section
            Text("Sort By", fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(8.dp))

            FlowRow(
                mainAxisSpacing = 8.dp,
                crossAxisSpacing = 8.dp,
                modifier = Modifier.fillMaxWidth()
            ) {
                SortOrder.entries.forEach { sort ->
                    FilterButton(
                        text = sort.buttonText,
                        isSelected = currentSortOrder == sort,
                        onClick = {
                            changeSort(sort)
//                            selectedSort = sort
                        }
                    )
                }
            }


            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                Button(
                    onClick = {
                        onContinueClick()
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp, start = 32.dp, end = 32.dp),
                    shape = RoundedCornerShape(12.dp),
                ) {
                    Text(
                        "Continue",
                    )
                }
            }

        }
    }
}


@Composable
fun FilterButton(text: String, isSelected: Boolean, onClick: () -> Unit) {
    Text(
        text = text,
        color = if (isSelected) Color(0xFF915AFF) else Color.Black,
        modifier = Modifier
            .clip(RoundedCornerShape(20.dp))
            .background(if (isSelected) Color(0xFFEEE3FF) else Color.Transparent)
            .border(1.dp, Color.LightGray, RoundedCornerShape(20.dp))
            .clickable { onClick() }
            .padding(horizontal = 16.dp, vertical = 8.dp),
        fontWeight = FontWeight.Medium
    )
}


@Preview(showBackground = true)
@Composable
private fun FilterTransactionUIPreview() {
//    FilterTransactionUI(selectedFilter, selectedSort, changeFilter(newFilter))
}


@Preview(showSystemUi = true)
@Composable
private fun TransactionItemPreview() {
    TransactionItem(
        Transaction(0, TransactionType.EXPENSE.transactionCode, "R", "R", 100, "R")
    )
}

