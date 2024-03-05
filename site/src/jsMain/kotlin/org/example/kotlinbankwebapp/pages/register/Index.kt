package org.example.kotlinbankwebapp.pages.register

import androidx.compose.runtime.*
import com.varabyte.kobweb.compose.css.Cursor
import com.varabyte.kobweb.compose.css.FontWeight
import com.varabyte.kobweb.compose.foundation.layout.Arrangement
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
import org.example.kotlinbankwebapp.data.addUser
import org.example.kotlinbankwebapp.model.User
import org.example.kotlinbankwebapp.navigation.Screen
import org.jetbrains.compose.web.attributes.InputType
import org.jetbrains.compose.web.css.px
import org.jetbrains.compose.web.dom.Button
import org.jetbrains.compose.web.dom.Input
import org.w3c.dom.HTMLInputElement

@Page
@Composable
fun RegisterPage() {
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
                text = "Enter the username",
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
                text = "Enter the password",
                modifier = Modifier
                    .fontSize(30.px)
                    .margin(20.px)
            )
            Input(
                type = InputType.Password,
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
                        if(username.isBlank()){
                            println("username is blank")
                        }else if(password.isBlank()){
                            println("password is blank")
                        }else{
                            println("adding user")
                            scope.launch {
                                addUser(
                                    User(
                                        username = username,
                                        password = password
                                    )
                                )
                            }
                            context.router.navigateTo(Screen.HomePage.route)
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
