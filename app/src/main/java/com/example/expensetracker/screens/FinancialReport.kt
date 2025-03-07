package com.example.expensetracker.screens

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
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
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.expensetracker.data.ChartModel
import com.example.expensetracker.data.TransactionType
import com.example.expensetracker.database.Transaction
import com.example.expensetracker.database.TransactionDatabase
import com.example.expensetracker.database.TransactionRepository
import com.example.expensetracker.model.TransactionViewModel

@Composable
fun FinancialReport(
    navController: NavController,
    modifier: Modifier = Modifier,
    transactionViewModel: TransactionViewModel
) {
//    var selectedTab by remember { mutableStateOf("Expense") }
    var selectedTabIndex by remember {
        mutableStateOf(0)
    }
    val tabItems = listOf("Expense", "Income")
    val pagerState = rememberPagerState {
        tabItems.size
    }
    val transactions by transactionViewModel.getAllItems.observeAsState(emptyList())
    LaunchedEffect(selectedTabIndex) {
        pagerState.animateScrollToPage(selectedTabIndex)
    }
    LaunchedEffect(pagerState.currentPage) {
//        if (!pagerState.isScrollInProgress) {
        selectedTabIndex = pagerState.currentPage
//        }
    }
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color(0xFFFFE0BD).copy(alpha = 0.2f))
    ) {
        DescBar("Financial Report", navController)
//        ExpenseIncomeTab(selectedTab) {
//            selectedTab = it
//        }
        TabRow(
            selectedTabIndex = selectedTabIndex
        ) {
            tabItems.forEachIndexed { index, item ->
                Tab(
                    selected = selectedTabIndex == index,
                    onClick = {
                        selectedTabIndex = index
                    },
                    text = {
                        Text(item)
                    }
                )
            }
        }

        HorizontalPager(
            state = pagerState,
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        ) { index ->
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Spacer(modifier = Modifier.height(16.dp))
                ShowTransaction(
//            selectedTab,
                    if (selectedTabIndex == 0) tabItems[0] else tabItems[1],
                    transactions.filter {
                        if (index == 0) it.type == TransactionType.EXPENSE else it.type == TransactionType.INCOME
                    }
                )
            }
        }

//        Spacer(modifier = Modifier.height(16.dp))
//        ShowTransaction(
////            selectedTab,
//            if(selectedTabIndex==0) tabItems[0] else tabItems[1],
//            transactionViewModel.getAllItems.observeAsState().value ?: emptyList()
//        )

//        ChartCirclePie(
//            charts = listOf(
//                ChartModel(transactionViewModel.expense.toFloat(), Color.Red),
//                ChartModel(transactionViewModel.income.toFloat(), Color.Blue)
//            ),
//            total=transactionViewModel.expense+transactionViewModel.income+0.0F
//        )
    }
}

@Composable
fun ShowTransaction(selectedTab: String, transactions: List<Transaction>) {
    LazyColumn(
        modifier = Modifier.fillMaxSize()
    ) {
        items(transactions) {
            ListItem(it)
        }

    }
}

@Composable
fun ListItem(data: Transaction, modifier: Modifier = Modifier) {
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
//@Composable
//fun ListItem(t: Transaction) {
//    val pre=if(t.type==TransactionType.INCOME)"+" else "-"
//    val color=if(t.type==TransactionType.INCOME)Color.Green else Color.Red
//    Row(
//        modifier = Modifier.fillMaxWidth()
//            .background(Color.White)
//            .padding(16.dp),
//        verticalAlignment = Alignment.CenterVertically,
//        horizontalArrangement = Arrangement.SpaceBetween
//    ){
//        Text(t.category, fontSize = 24.sp)
//        Text(pre+t.amount.toString(), fontSize = 24.sp, color = color)
//    }
//}


@Preview(showBackground = true)
@Composable
private fun ListItemPreview() {
    ListItem(Transaction(1, TransactionType.INCOME, "Bills", "Shopping", 100, "abc"))
}

@Composable
fun ExpenseIncomeTab(selectedTab: String, onTabSelected: (String) -> Unit) {
    val tabs = listOf("Expense", "Income")
    Row(
        Modifier
            .fillMaxWidth()
            .padding(4.dp)
            .background(Color(0xFFFFF9EC), shape = RoundedCornerShape(16.dp)) // Background color
            .padding(8.dp)
    ) {
        tabs.forEach { tab ->
            val isSelected = selectedTab == tab
            Box(
                modifier = Modifier
                    .weight(1f)
                    .clip(RoundedCornerShape(16.dp))
                    .background(
                        if (isSelected) {
                            if (tab == "Income") Color.Green
                            else Color.Red
                        } else Color(0xFFF3F5F8)
                    )
                    .clickable { onTabSelected(tab) }
                    .padding(vertical = 8.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = tab,
                    color = if (isSelected) Color.White else Color.Black,
                    style = MaterialTheme.typography.headlineMedium
                )
            }
        }
    }
}


@Composable
private fun ChartCirclePie(
    modifier: Modifier = Modifier,
    charts: List<ChartModel>,
    total: Float,
    size: Dp = 200.dp,
    strokeWidth: Dp = 16.dp
) {
    Canvas(
        modifier = modifier
            .size(size)
            .background(Color.LightGray)
            .padding(12.dp),
        onDraw = {
            var startAngle = 0f

            charts.forEach { chart ->
                val sweepAngle = (chart.value / total) * 360

                drawArc(
                    color = chart.color,
                    startAngle = startAngle,
                    sweepAngle = sweepAngle,
                    useCenter = false,
                    style = Stroke(
                        width = strokeWidth.toPx(),
                        cap = StrokeCap.Round,
                        join = StrokeJoin.Round
                    )
                )

                startAngle += sweepAngle
            }
        }
    )
}

data class TabItem(
    val title: String,
    val unselectedIcon: ImageVector
)


@Preview(showSystemUi = true)
@Composable
private fun StatisticsPreview() {
    FinancialReport(
        rememberNavController(), transactionViewModel = TransactionViewModel(
            TransactionRepository(
                TransactionDatabase.getDatabase(context = LocalContext.current).transactionDao()
            )
        )
    )
}