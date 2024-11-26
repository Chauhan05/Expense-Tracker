package com.example.expensetracker.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.max
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.expensetracker.data.TransactionDetailedData
import com.example.expensetracker.data.TransactionType
import com.example.expensetracker.data.transactionList
import com.example.expensetracker.database.Transaction
import com.example.expensetracker.navigation.NavigationItem

@Composable
fun Transaction(
    navController: NavController,
    transactions: List<Transaction>,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color(0xFFFFE0BD).copy(alpha = 0.4f))
    ) {
        DescBar("Transaction",navController)
        TransactionList(transactions)
    }
}

@Composable
fun TransactionList(transaction: List<Transaction>, modifier: Modifier = Modifier) {
    LazyColumn(
        modifier.fillMaxSize()
    ) {
        items(transaction) { item ->
            TransactionItem(item)
        }
    }
}

@Composable
fun TransactionItem(data: Transaction, modifier: Modifier = Modifier) {
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
                if (data.type == TransactionType.INCOME) "+" + data.amount.toString() else "-" + data.amount.toString(),
                color = if (data.type == TransactionType.INCOME) Color.Green else Color.Red,
                fontSize = 22.sp,
                maxLines = 1
            )
            Text(
                data.time,
                color = Color.DarkGray.copy(0.7f),
                modifier = Modifier.padding(top = 4.dp),
                maxLines = 1
//                text
            )
        }
    }
}

@Preview(showSystemUi = true)
@Composable
private fun TransactionItemPreview() {
    TransactionItem(
        Transaction(0,TransactionType.EXPENSE,"R","R",100,"R")
    )
}

