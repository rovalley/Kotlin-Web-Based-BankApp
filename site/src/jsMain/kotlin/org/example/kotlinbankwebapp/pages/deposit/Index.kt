package org.example.kotlinbankwebapp.pages.deposit

import androidx.compose.runtime.*
import com.varabyte.kobweb.compose.css.Cursor
import com.varabyte.kobweb.compose.css.FontWeight
import com.varabyte.kobweb.compose.foundation.layout.Arrangement
import com.varabyte.kobweb.compose.foundation.layout.Box
import com.varabyte.kobweb.compose.foundation.layout.Column
import com.varabyte.kobweb.compose.foundation.layout.Row
import com.varabyte.kobweb.compose.ui.Alignment
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.*
import com.varabyte.kobweb.compose.ui.toAttrs
import com.varabyte.kobweb.core.Page
import com.varabyte.kobweb.core.rememberPageContext
import com.varabyte.kobweb.silk.components.text.SpanText
import kotlinx.browser.document
import kotlinx.coroutines.launch
import org.example.kotlinbankwebapp.Transaction
import org.example.kotlinbankwebapp.components.HeaderLayout
import org.example.kotlinbankwebapp.data.addTransaction
import org.example.kotlinbankwebapp.data.getCurrentUser
import org.example.kotlinbankwebapp.navigation.Screen
import org.jetbrains.compose.web.attributes.InputType
import org.jetbrains.compose.web.css.px
import org.jetbrains.compose.web.dom.Button
import org.jetbrains.compose.web.dom.Input
import org.jetbrains.compose.web.dom.Text
import org.w3c.dom.HTMLInputElement

fun isNumber(value:String):Boolean{
    for(char in value){
        if(char == '0' || char =='1' || char == '2' || char == '3' || char == '4' || char =='5' ||
            char == '6' || char =='7' || char == '8' || char == '9' || char == '.'){
            continue
        }else{
            return false
        }
    }
    return true
}

@Page
@Composable
fun DepositPage() {

    val context = rememberPageContext()
    val scope = rememberCoroutineScope()
    var username by remember { mutableStateOf<String>("") }

    LaunchedEffect(Unit){
        if (getCurrentUser().equals("logout")) {
            context.router.navigateTo(Screen.LoginPage.route)
        }
        username = getCurrentUser()
    }

    HeaderLayout(
        context = context
    ){
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ){
            SpanText(
                text = "Enter the amount to deposit",
                modifier = Modifier
                    .fontSize(30.px)
                    .margin(20.px)
            )
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ){
                Input(
                    type = InputType.Text,
                    attrs = Modifier
                        .id("deposit")
                        .width(300.px)
                        .height(50.px)
                        .padding(25.px)
                        .borderRadius(10.px)
                        .margin(right = 30.px)
                        .toAttrs {
                            attr("placeholder", "enter amount")
                        }
                )
                Button(
                    attrs = Modifier
                        .onClick {
                            val amount = (document.getElementById("deposit") as HTMLInputElement).value
                            if(amount.isBlank()){
                                println("blank")
                            }else if(!org.example.kotlinbankwebapp.pages.withdraw.isNumber(amount)){
                                println("Not a valid amount")
                            }else{
                                scope.launch {
                                    addTransaction(
                                        Transaction(
                                            transactionType = "Deposit",
                                            amount = amount.toDouble(),
                                            userName = username.toString()
                                        )
                                    )
                                }
                                context.router.navigateTo(Screen.DashboardPage.route)
                            }
                        }
                        .height(50.px)
                        .cursor(Cursor.Pointer)
                        .padding(leftRight = 30.px)
                        .toAttrs()
                ){
                    SpanText(
                        text = "Submit",
                        modifier = Modifier
                            .fontSize(18.px)
                            .fontWeight(FontWeight.Medium)
                    )
                }
            }
        }



    }
}
