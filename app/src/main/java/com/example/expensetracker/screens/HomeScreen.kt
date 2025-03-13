package com.example.expensetracker.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDownward
import androidx.compose.material.icons.filled.ArrowUpward
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.expensetracker.data.TransactionType
import com.example.expensetracker.database.TransactionDatabase
import com.example.expensetracker.database.TransactionRepository
import com.example.expensetracker.model.TransactionViewModel
import com.example.expensetracker.navigation.NavigationItem
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Locale

@Composable
fun HomeScreen(
    navController: NavController,
    modifier: Modifier = Modifier,
    transactionViewModel: TransactionViewModel
) {
//    val allItems = transactionViewModel.getAllItems.observeAsState()
//
//    LaunchedEffect(allItems.value) {
//        transactionViewModel.calIncomeExpense(allItems.value ?: listOf())
//    }

    Column(
        modifier = modifier.fillMaxSize(),
    ) {
//        Spacer(modifier = Modifier.height(100.dp))
        UserDetails(transactionViewModel)
        Spacer(Modifier.height(16.dp))
        RecentTransaction(transactionViewModel,navController)
    }
}

@Composable
fun UserDetails(transactionViewModel: TransactionViewModel, modifier: Modifier = Modifier) {
    ElevatedCard(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.elevatedCardColors(
            containerColor = Color(0xFFFFE0BD).copy(alpha = 0.5f) // Light skin color
        ),
        shape = RoundedCornerShape(bottomEnd = 16.dp, bottomStart = 16.dp)
    ) {
        Column(
            modifier = Modifier.padding(bottom = 8.dp, top = 8.dp, start = 4.dp, end = 4.dp)
        ) {
            ProfileDetails()
            HorizontalDivider(
                thickness = 1.dp,
                color = Color.Black,
                modifier = Modifier.padding(top = 4.dp, start = 16.dp, end = 16.dp)
            )
            AccountDetails(transactionViewModel)
            MoneyDetails(transactionViewModel)
        }
    }
}

@Composable
fun RecentTransaction(transactionViewModel: TransactionViewModel, navController: NavController) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {

        Text("Recent Transaction", fontSize = 20.sp)
        TextButton(onClick = {
            navController.navigate(NavigationItem.Transaction.route){
                popUpTo(navController.graph.startDestinationId) { inclusive = false }
                launchSingleTop = true
                restoreState = true
            }
        }) {
            Text("View All", fontSize = 18.sp,)
        }
//        Text("View All", fontSize = 20.sp)
    }
    TransactionListHomeScreen(
        transactionViewModel.allItemsLiveData.observeAsState().value ?: emptyList()
    )

//    TransactionItem(Icons.Default.ArrowDownward,"15000","Income")
}

@Composable
fun TransactionListHomeScreen(
    transactions: List<com.example.expensetracker.database.Transaction>,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier.fillMaxSize()
    ) {
        items(transactions) { transaction ->
            TransactionItem(
                amount = transaction.amount.toString(),
                title = transaction.category,
                type = if(transaction.type==0) TransactionType.INCOME else TransactionType.EXPENSE ,
            )
        }
    }
}

@Composable
fun TransactionItem(
    amount: String,
    title: String,
    type: TransactionType,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp) // Padding around the row
            .background(Color(0xFFD9D9D9).copy(alpha = 0.5f), shape = RoundedCornerShape(8.dp))
            .padding(12.dp), // Inner padding for content
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = if (type == TransactionType.INCOME) Icons.Default.ArrowDownward else Icons.Default.ArrowUpward,
            contentDescription = null,
            tint = Color.White,
            modifier = Modifier
                .clip(CircleShape)
                .size(48.dp)
                .background(
                    if (type == TransactionType.INCOME) Color(0xFF00A36A) else Color(
                        0xFFFF5252
                    )
                )
                .padding(8.dp)
        )

        // Use Modifier.weight to give more space for the amount
        Text(
            text = "₹$amount",
            fontSize = 24.sp,
            modifier = Modifier
                .padding(start = 16.dp)
                .weight(1f) // Allows the amount to take up more space
        )
        Text(
            text = title,
            fontSize = 18.sp,
            modifier = Modifier.padding(end = 4.dp), // Smaller padding to create a subtle separation
            fontWeight = FontWeight.Bold
        )
    }
}


@Composable
fun MoneyDetails(transactionViewModel: TransactionViewModel) {
    Row(
        modifier = Modifier.fillMaxWidth(),
    ) {
        AmountCard(
            "Income",
            transactionViewModel.income.toString(),
            Color(0xFF00A36A),
            Icons.Default.ArrowDownward,
            modifier = Modifier.weight(1f)
        )
        AmountCard(
            "Expense",
            transactionViewModel.expense.toString(),
            Color(0xFFFF5252),
            Icons.Default.ArrowUpward,
            modifier = Modifier.weight(1f)
        )
    }
}

@Composable
fun AccountDetails(transactionViewModel: TransactionViewModel) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(Modifier.height(40.dp))
        Text("Account Balance", fontSize = 18.sp)
        Text(
            transactionViewModel.netIncome.toString(),
            fontSize = 28.sp,
            modifier = Modifier.padding(top = 4.dp, bottom = 8.dp),
            fontWeight = FontWeight.Bold,
            maxLines = 1
        )
    }
}

@Composable
fun AmountCard(
    title: String,
    amount: String,
    backgroundColor: Color,
    icon: ImageVector,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .padding(8.dp)
            .background(Color.Transparent),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = backgroundColor),
        elevation = CardDefaults.elevatedCardElevation(4.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Icon section (you can replace this with a custom wallet icon if needed)
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = Color.Unspecified,
                modifier = Modifier
                    .size(48.dp)
                    .background(Color(0xFFFFFFFF), shape = RoundedCornerShape(8.dp))
                    .padding(8.dp)
            )

            Spacer(modifier = Modifier.width(16.dp))

            // Text section
            Column {
                Text(
                    text = title,
                    color = Color.White,
                    style = MaterialTheme.typography.bodyMedium.copy(
                        fontWeight = FontWeight.Bold,
                        fontSize = 14.sp
                    )
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = amount,
                    color = Color.White,
                    style = MaterialTheme.typography.titleLarge.copy(
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp
                    ),
                    maxLines = 1
                )
            }
        }
    }
}

@Composable
fun ProfileDetails(modifier: Modifier = Modifier) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        val currentDate = LocalDate.now()

        // Formatter for date (e.g. "October 17, 2024")
        val formattedDate =
            currentDate.format(
                DateTimeFormatter.ofPattern(
                    "MMMM dd, yyyy",
                    Locale.getDefault()
                )
            )

        // Get day of the week (e.g. "Thursday")
        val dayOfWeek =
            currentDate.dayOfWeek.name.lowercase().replaceFirstChar { it.uppercase() }
        Text("$formattedDate\n$dayOfWeek", fontSize = 22.sp)
        Spacer(modifier = Modifier.weight(1f))
        Icon(Icons.Default.Person, "profile")
        Text("Nitin", fontSize = 22.sp)
    }
}


@Preview
@Composable
private fun TransactionItemPreview() {

    TransactionItem(200.toString(), "Rdfas", TransactionType.EXPENSE)
//    TransactionItem(200.toString(), "R", TransactionType.EXPENSE)
}

@SuppressLint("NewApi")
@Preview(showSystemUi = true)
@Composable
private fun HomeScreenPreview() {

    HomeScreen(
        rememberNavController(), Modifier, transactionViewModel = TransactionViewModel(
            TransactionRepository(
                TransactionDatabase.getDatabase(context = LocalContext.current).transactionDao()
            )
        )
    )
}