package com.example.expensetracker.navigation

enum class Screen{
    HOME,
    TRANSACTION,
    ADD,
    FINANCIALREPORT,
    PROFILE
}

sealed class NavigationItem(val  route:String,val title:String){
    data object Home:NavigationItem(Screen.HOME.name,"Home")
    data object Transaction:NavigationItem(Screen.TRANSACTION.name,"History")
    data object Add:NavigationItem(Screen.ADD.name,"Add")
    data object FinancialReport:NavigationItem(Screen.FINANCIALREPORT.name,"Report")
    data object Profile:NavigationItem(Screen.PROFILE.name,"Profile")
}
