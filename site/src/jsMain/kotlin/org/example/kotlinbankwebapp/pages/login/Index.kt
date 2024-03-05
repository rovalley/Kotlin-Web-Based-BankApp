package org.example.kotlinbankwebapp.pages.login

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
import org.example.kotlinbankwebapp.data.login
import org.example.kotlinbankwebapp.navigation.Screen
import org.jetbrains.compose.web.attributes.InputType
import org.jetbrains.compose.web.css.px
import org.jetbrains.compose.web.dom.Button
import org.jetbrains.compose.web.dom.Input
import org.jetbrains.compose.web.dom.Text
import org.w3c.dom.HTMLInputElement

@Page
@Composable
fun LoginPage() {
    val context = rememberPageContext()
    val scope = rememberCoroutineScope()

        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ){

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ){
                SpanText(
                    text = "Username: ",
                    modifier = Modifier
                        .fontSize(30.px)
                        .margin(20.px)
                )
                Input(
                    type = InputType.Text,
                    attrs = Modifier
                        .id("username")
                        .width(300.px)
                        .height(50.px)
                        .padding(25.px)
                        .borderRadius(10.px)
                        .margin(right = 30.px)
                        .toAttrs {
                            attr("placeholder", "enter username")
                        }
                )

            }
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ){
                SpanText(
                    text = "Password: ",
                    modifier = Modifier
                        .fontSize(30.px)
                        .margin(20.px)
                )
                Input(
                    type = InputType.Text,
                    attrs = Modifier
                        .id("password")
                        .width(300.px)
                        .height(50.px)
                        .padding(25.px)
                        .borderRadius(10.px)
                        .margin(right = 30.px)
                        .toAttrs {
                            attr("placeholder", "enter password")
                        }
                )

            }
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ){
                Button(
                    attrs = Modifier
                        .onClick {
                            val username = (document.getElementById("username") as HTMLInputElement).value
                            val password = (document.getElementById("password") as HTMLInputElement).value
                            scope.launch {
                                login(username, password)
                                if(!getCurrentUser().equals("logout")){
                                    context.router.navigateTo(Screen.DashboardPage.route)
                                }
                            }
                        }
                        .height(50.px)
                        .cursor(Cursor.Pointer)
                        .padding(leftRight = 30.px)
                        .toAttrs()
                ){
                    SpanText(
                        text = "Login",
                        modifier = Modifier
                            .fontSize(18.px)
                            .fontWeight(FontWeight.Medium)
                    )
                }

            }
        }

}
