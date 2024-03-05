package org.example.kotlinbankwebapp.pages.home

import androidx.compose.runtime.*
import com.varabyte.kobweb.compose.css.Cursor
import com.varabyte.kobweb.compose.foundation.layout.Arrangement
import com.varabyte.kobweb.compose.foundation.layout.Box
import com.varabyte.kobweb.compose.foundation.layout.Column
import com.varabyte.kobweb.compose.foundation.layout.Row
import com.varabyte.kobweb.compose.ui.Alignment
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.graphics.Colors
import com.varabyte.kobweb.compose.ui.modifiers.*
import com.varabyte.kobweb.core.Page
import com.varabyte.kobweb.core.rememberPageContext
import com.varabyte.kobweb.silk.components.text.SpanText
import org.example.kotlinbankwebapp.components.HeaderLayout
import org.example.kotlinbankwebapp.navigation.Screen
import org.jetbrains.compose.web.css.px
import org.jetbrains.compose.web.dom.Text

@Page
@Composable
fun HomePage() {
    val context = rememberPageContext()
    val scope = rememberCoroutineScope()

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Row (
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            SpanText(
                text = "Register Account",
                modifier = Modifier
                    .cursor(Cursor.Pointer)
                    .margin(20.px)
                    .onClick {context.router.navigateTo(Screen.RegisterPage.route)}
            )
            SpanText(
                text = "Log in",
                modifier = Modifier
                    .cursor(Cursor.Pointer)
                    .margin(20.px)
                    .onClick {context.router.navigateTo(Screen.LoginPage.route)}
            )
        }
    }

}
