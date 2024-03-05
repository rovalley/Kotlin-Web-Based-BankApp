package org.example.kotlinbankwebapp.components

import androidx.compose.runtime.Composable
import com.varabyte.kobweb.compose.css.Cursor
import com.varabyte.kobweb.compose.foundation.layout.Arrangement
import com.varabyte.kobweb.compose.foundation.layout.Box
import com.varabyte.kobweb.compose.foundation.layout.Column
import com.varabyte.kobweb.compose.foundation.layout.Row
import com.varabyte.kobweb.compose.ui.Alignment
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.graphics.Colors
import com.varabyte.kobweb.compose.ui.modifiers.*
import com.varabyte.kobweb.core.PageContext
import com.varabyte.kobweb.silk.components.text.SpanText
import org.example.kotlinbankwebapp.navigation.Screen
import org.jetbrains.compose.web.css.Color
import org.jetbrains.compose.web.css.px

@Composable
fun HeaderLayout(
    context: PageContext,
    content: @Composable ()->Unit
) {
    Box(
        modifier = Modifier.fillMaxSize()
    ){
        Row(
            modifier = Modifier
                .background(Colors.Blue)
                .padding(10.px)
                .fillMaxWidth()
                .height(100.px),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ){
            SpanText(
                text = "Dashboard",
                modifier = Modifier
                    .color(Colors.White)
                    .cursor(Cursor.Pointer)
                    .margin(20.px)
                    .onClick {context.router.navigateTo(Screen.DashboardPage.route)}
            )
            SpanText(
                text = "Balance",
                modifier = Modifier
                    .color(Colors.White)
                    .cursor(Cursor.Pointer)
                    .margin(20.px)
                    .onClick {context.router.navigateTo(Screen.BalancePage.route)}
            )
            SpanText(
                text = "Deposit",
                modifier = Modifier
                    .color(Colors.White)
                    .cursor(Cursor.Pointer)
                    .margin(20.px)
                    .onClick {context.router.navigateTo(Screen.DepositPage.route)}
            )
            SpanText(
                text = "Withdraw",
                modifier = Modifier
                    .color(Colors.White)
                    .cursor(Cursor.Pointer)
                    .onClick {context.router.navigateTo(Screen.WithdrawPage.route)}
            )
            SpanText(
                text = "Transactions",
                modifier = Modifier
                    .color(Colors.White)
                    .cursor(Cursor.Pointer)
                    .margin(20.px)
                    .onClick {context.router.navigateTo(Screen.TransactionsPage.route)}
            )
            SpanText(
                text = "Log out",
                modifier = Modifier
                    .color(Colors.White)
                    .cursor(Cursor.Pointer)
                    .onClick {


                        context.router.navigateTo(Screen.HomePage.route)
                    }
            )

        }
        Column(
            modifier = Modifier
                .fillMaxSize()
                .margin(top = 100.px)
        ){
            content()
        }
    }
}