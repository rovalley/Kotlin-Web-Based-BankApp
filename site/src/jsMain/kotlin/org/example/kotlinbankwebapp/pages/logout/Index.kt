package org.example.kotlinbankwebapp.pages.logout

import androidx.compose.runtime.*
import com.varabyte.kobweb.compose.foundation.layout.Box
import com.varabyte.kobweb.compose.ui.Alignment
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.fillMaxSize
import com.varabyte.kobweb.core.Page
import com.varabyte.kobweb.core.rememberPageContext
import org.example.kotlinbankwebapp.components.HeaderLayout
import org.example.kotlinbankwebapp.data.logOut
import org.example.kotlinbankwebapp.navigation.Screen
import org.jetbrains.compose.web.dom.Text

@Page
@Composable
fun Logout() {
    val context = rememberPageContext()
    val scope = rememberCoroutineScope()
    LaunchedEffect(Unit){
        logOut()
        context.router.navigateTo(Screen.HomePage.route)
    }
}
