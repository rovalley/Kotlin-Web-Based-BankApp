package org.example.kotlinbankwebapp.navigation

sealed class Screen(val route:String){
    object HomePage: Screen(route = "/home")
    object DashboardPage: Screen(route = "/dashboard")
    object BalancePage:Screen(route = "/balance")
    object DepositPage:Screen(route = "/deposit")
    object WithdrawPage:Screen(route = "/withdraw")
    object TransactionsPage:Screen(route = "/transactions")
    object LoginPage:Screen(route = "/login")
    object RegisterPage:Screen(route = "/register")
}