package org.example.kotlinbankwebapp.pages.transactions

import androidx.compose.runtime.*
import com.varabyte.kobweb.compose.foundation.layout.Arrangement
import com.varabyte.kobweb.compose.foundation.layout.Box
import com.varabyte.kobweb.compose.foundation.layout.Column
import com.varabyte.kobweb.compose.foundation.layout.Row
import com.varabyte.kobweb.compose.ui.Alignment
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.fillMaxHeight
import com.varabyte.kobweb.compose.ui.modifiers.fillMaxSize
import com.varabyte.kobweb.compose.ui.modifiers.fontSize
import com.varabyte.kobweb.compose.ui.modifiers.margin
import com.varabyte.kobweb.core.Page
import com.varabyte.kobweb.core.rememberPageContext
import com.varabyte.kobweb.silk.components.text.SpanText
import org.example.kotlinbankwebapp.components.HeaderLayout
import org.example.kotlinbankwebapp.data.getAllTransactions
import org.example.kotlinbankwebapp.data.getCurrentUser
import org.example.kotlinbankwebapp.model.ApiListResponse
import org.example.kotlinbankwebapp.navigation.Screen
import org.jetbrains.compose.web.css.px
import org.jetbrains.compose.web.dom.Text

@Page
@Composable
fun TransactionsPage() {
    val context = rememberPageContext()
    var response by remember{mutableStateOf<ApiListResponse>(ApiListResponse.Loading)}
    var username by remember { mutableStateOf<String>("") }
    LaunchedEffect(Unit){
        if (getCurrentUser().equals("logout")) {
            context.router.navigateTo(Screen.LoginPage.route)
        }
        username = getCurrentUser()
        getAllTransactions(
            onSuccess = {
                if(it is ApiListResponse.Success){
                    response = it
                }
            },
            onError = {
                println("Error")
                response = ApiListResponse.Error(it.message.toString())
            }
        )
    }
    HeaderLayout(
        context = context
    ){
        Column(
            modifier = Modifier
                .margin(40.px)
                .fillMaxHeight(),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            when(response){
                is ApiListResponse.Success->{
                    (response as ApiListResponse.Success).data.forEach{transaction ->
                        if (transaction.userName.equals(username)) {
                            Row() {
                                SpanText(
                                    text = transaction.transactionType,
                                    modifier = Modifier
                                        .fontSize(24.px)
                                        .margin(12.px)
                                )
                                SpanText(
                                    text = transaction.amount.toString(),
                                    modifier = Modifier
                                        .fontSize(24.px)
                                        .margin(12.px)
                                )
                            }
                        }
                    }
                }
                else->{
                    println("Error")
                }
            }
        }
    }
}
