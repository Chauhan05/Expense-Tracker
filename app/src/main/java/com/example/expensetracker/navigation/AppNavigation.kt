package com.example.expensetracker.navigation

enum class Screen{
    HOME,
    TRANSACTION,
    ADD,
    STATISTICS,
    PROFILE
}

sealed class NavigationItem(val  route:String,val title:String){
    data object Home:NavigationItem(Screen.HOME.name,"Home")
    data object Transaction:NavigationItem(Screen.TRANSACTION.name,"History")
    data object Add:NavigationItem(Screen.ADD.name,"Add")
    data object Statistics:NavigationItem(Screen.STATISTICS.name,"Statistic")
    data object Profile:NavigationItem(Screen.PROFILE.name,"Profile")
}