package org.example.kotlinbankwebapp.pages.dashboard

import androidx.compose.runtime.*
import com.varabyte.kobweb.compose.foundation.layout.Box
import com.varabyte.kobweb.compose.ui.Alignment
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.fillMaxSize
import com.varabyte.kobweb.core.Page
import com.varabyte.kobweb.core.rememberPageContext
import org.example.kotlinbankwebapp.components.HeaderLayout
import org.example.kotlinbankwebapp.data.getCurrentUser
import org.example.kotlinbankwebapp.data.logOut
import org.example.kotlinbankwebapp.navigation.Screen
import org.jetbrains.compose.web.dom.Text

@Page
@Composable
fun DashboardPage() {
    val context = rememberPageContext()
    val scope = rememberCoroutineScope()

    LaunchedEffect(Unit){
        if (getCurrentUser().equals("logout")) {
            context.router.navigateTo(Screen.LoginPage.route)
        }
    }
    HeaderLayout(
        context = context
    ){

    }
}
