package com.example.expensetracker.screens

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.expensetracker.data.TransactionType
import com.example.expensetracker.database.Transaction
import com.example.expensetracker.database.TransactionDatabase
import com.example.expensetracker.database.TransactionRepository
import com.example.expensetracker.model.TransactionViewModel
import com.example.expensetracker.navigation.NavigationItem
import com.example.expensetracker.ui_components.DatePickerDocked

@Composable
fun Add(
    navController: NavController,
    modifier: Modifier = Modifier,
    transactionViewModel: TransactionViewModel
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color(0xFFFFE0BD).copy(alpha = 0.5f))
//            .padding(top = 40.dp)
    ) {
        DescBar("Add Transaction",navController)
        Spacer(modifier = Modifier.height(24.dp))
        TransactionDetail(transactionViewModel)
    }
}

@Composable
fun TransactionDetail(transactionViewModel: TransactionViewModel) {
    val context = LocalContext.current
    Text("How much?", fontSize = 20.sp, modifier = Modifier.padding(start = 12.dp, bottom = 4.dp))
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text("₹", modifier = Modifier.padding(horizontal = 4.dp), fontSize = 24.sp)
        OutlinedTextField(
            if (transactionViewModel.money == 0) "" else transactionViewModel.money.toString(),
            onValueChange = { m ->
                if (m.trim().isEmpty()) {
                    transactionViewModel.changeMoney(0)
                } else {
                    transactionViewModel.changeMoney(m.toInt())
                }
            },
            label = {
                Text("Money")
            },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(8.dp),
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Number
            ),
            maxLines = 1
        )
    }
    Spacer(modifier = Modifier.height(20.dp))
    ElevatedCard(
        modifier = Modifier.fillMaxSize(),
        colors = CardDefaults.elevatedCardColors(
            containerColor = Color.White // Light skin color
        ),
        shape = RoundedCornerShape(topStart = 40.dp, topEnd = 40.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            Spacer(modifier = Modifier.padding(24.dp))
            CategorySelection(transactionViewModel)
//            Text(
//                "Category",
//                fontSize = 22.sp,
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .padding(bottom = 4.dp),
//                textAlign = TextAlign.Center,
//            )
//            Row(    CategorySelection(TransactionViewModel(TransactionRepository()))

//                modifier = Modifier
//                    .fillMaxWidth()
//                    .padding(top = 8.dp, bottom = 4.dp),
//                horizontalArrangement = Arrangement.SpaceAround
//            ) {
//                ButtonCmp("Income", Color.Green)
//                ButtonCmp("Expense", Color.Red)
//            }
            OutlinedTextField(
                transactionViewModel.category,
                onValueChange = {
                    transactionViewModel.changeCategory(it)
                },
                label = {
                    Text("Category")
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                maxLines = 1
            )
            OutlinedTextField(
                transactionViewModel.description,
                onValueChange = {
                    transactionViewModel.changeDescription(it)
                },
                label = {
                    Text("Description")
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                maxLines = 1
            )
            DatePickerDocked(transactionViewModel)
            Button(
                onClick =
                {
                    transactionViewModel.changeToastMessage(checkValidData(transactionViewModel))

                },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF7F3DFF)),
                shape = RoundedCornerShape(12.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
            ) {
                Text("Continue", fontSize = 20.sp)
            }
//            Spacer(Modifier.height(120.dp))
        }


    }

    LaunchedEffect(transactionViewModel.toastMessage) {
        if (transactionViewModel.toastMessage.isNotEmpty()) {
            Toast.makeText(context, transactionViewModel.toastMessage, Toast.LENGTH_SHORT).show()
            transactionViewModel.changeToastMessage("") // Clear the message after showing the toast
        }
    }
}


fun checkValidData(transactionViewModel: TransactionViewModel): String {
    val money = transactionViewModel.money
    val category = transactionViewModel.category
    val description = transactionViewModel.description
    val date = transactionViewModel.date
    val type =
        if (transactionViewModel.menuItem == "Income") TransactionType.INCOME else TransactionType.EXPENSE
    if (money!=0 && category.trim().isNotEmpty() && description.trim().isNotEmpty() && date.trim()
            .isNotEmpty()
    ) {
//            .height(64.dp)
        if(type==TransactionType.EXPENSE && transactionViewModel.money>transactionViewModel.netIncome){
            return "Insufficient Balance"
        }
        transactionViewModel.insert(Transaction(0, type.transactionCode, category, description, money, date))
//        if (type == TransactionType.INCOME) {
//            transactionViewModel.increaseIncome(money)
//        } else {
//            transactionViewModel.increaseExpense(money)
//        }
        transactionViewModel.clearData()
        return "Data inserted successfully"
//        Toast.makeText(LocalContext.current, "Data inserted successfully", Toast.LENGTH_LONG).show()
    } else {
        return "Please fill all the data"
//        Toast.makeText(LocalContext.current, "Please Fill all the data", Toast.LENGTH_LONG).show()
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CategorySelection(transactionViewModel: TransactionViewModel) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text("Type", fontSize = 22.sp)

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 4.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            ExposedDropdownMenuBox(
                expanded = transactionViewModel.isDropDownExpanded,
                onExpandedChange = {
                    transactionViewModel.toggleDropDownExpandedValue()
                }
            ) {
                TextField(
                    modifier = Modifier
                        .menuAnchor()
                        .fillMaxWidth(),
                    value = transactionViewModel.menuItem,
                    onValueChange = {},
                    readOnly = true,
                    trailingIcon = {
                        ExposedDropdownMenuDefaults.TrailingIcon(
                            expanded = transactionViewModel.isDropDownExpanded
                        )
                    },
                )
                ExposedDropdownMenu(
                    expanded = transactionViewModel.isDropDownExpanded,
                    onDismissRequest = {
                        transactionViewModel.changeDropDownExpandedValue(false)
//                        isExpanded = false
                    },
                ) {
                    transactionViewModel.itemList.forEachIndexed { index, s ->
                        DropdownMenuItem(
                            text = { Text(s) },
                            onClick = {
                                transactionViewModel.changeMenuItem(transactionViewModel.itemList[index])
                                transactionViewModel.changeDropDownExpandedValue(false)
//                                selectedText = itemsList[index]
//                                isExpanded = false
                            },
                            contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding
                        )
                    }
                }

            }
        }
    }
}

@Composable
fun ButtonCmp(title: String, color: Color) {
    Button(
        onClick = {},
        colors = ButtonDefaults.buttonColors(containerColor = color),
        shape = RoundedCornerShape(8.dp),
    ) {
        Text(title, fontWeight = FontWeight.Bold, color = Color.White)
    }
}

@Composable
fun DescBar(title: String,navController: NavController) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(
            onClick = {
                navController.navigate(NavigationItem.Home.route){
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
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center
        )
    }

}


@Preview
@Composable
private fun AddPreview() {
    Add(
        rememberNavController(), Modifier, transactionViewModel = TransactionViewModel(
            TransactionRepository(
                TransactionDatabase.getDatabase(context = LocalContext.current).transactionDao()
            )
        )
    )
}