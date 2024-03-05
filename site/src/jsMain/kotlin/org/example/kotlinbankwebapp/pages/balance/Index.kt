package org.example.kotlinbankwebapp.pages.balance

import androidx.compose.runtime.*
import com.varabyte.kobweb.compose.foundation.layout.Arrangement
import com.varabyte.kobweb.compose.foundation.layout.Box
import com.varabyte.kobweb.compose.foundation.layout.Column
import com.varabyte.kobweb.compose.foundation.layout.Row
import com.varabyte.kobweb.compose.ui.Alignment
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.fillMaxSize
import com.varabyte.kobweb.compose.ui.modifiers.fontSize
import com.varabyte.kobweb.compose.ui.modifiers.margin
import com.varabyte.kobweb.core.Page
import com.varabyte.kobweb.core.rememberPageContext
import com.varabyte.kobweb.silk.components.text.SpanText
import org.example.kotlinbankwebapp.components.HeaderLayout
import org.example.kotlinbankwebapp.data.getAllTransactions
import org.example.kotlinbankwebapp.data.getBalance
import org.example.kotlinbankwebapp.data.getCurrentUser
import org.example.kotlinbankwebapp.model.ApiListResponse
import org.example.kotlinbankwebapp.navigation.Screen
import org.jetbrains.compose.web.css.px
import org.jetbrains.compose.web.dom.Text

@Page
@Composable
fun BalancePage() {
    val context = rememberPageContext()
    var balance by remember { mutableDoubleStateOf(0.0) }


    LaunchedEffect(Unit) {
        if (getCurrentUser().equals("logout")) {
            context.router.navigateTo(Screen.LoginPage.route)
        }
        balance = getBalance().toDouble()
    }
    HeaderLayout(
        context = context
    ){
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ){
            Row(){
                SpanText(
                    text = "Balance",
                    modifier = Modifier
                        .fontSize(24.px)
                        .margin(12.px)
                )
                SpanText(
                    text = balance.toString(),
                    modifier = Modifier
                        .fontSize(24.px)
                        .margin(12.px)
                )
            }
        }
    }
}
