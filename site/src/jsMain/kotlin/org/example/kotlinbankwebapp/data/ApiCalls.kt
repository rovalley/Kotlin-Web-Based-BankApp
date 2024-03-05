package org.example.kotlinbankwebapp.data

import com.varabyte.kobweb.browser.api
import kotlinx.browser.window
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.example.kotlinbankwebapp.Transaction
import org.example.kotlinbankwebapp.model.ApiListResponse
import org.example.kotlinbankwebapp.model.CurrentUser
import org.example.kotlinbankwebapp.model.User


suspend fun login(userName:String, password:String){
    val users = window.api.tryGet(
        apiPath = "getusers"
    )?.decodeToString()
    if (users != null && users.contains("\"username\":\"" + userName +
    "\",\"password\":\"" + password + "\"")){
        println("logged in")

        var currentUser = CurrentUser("", userName)

        window.api.tryPost(
            apiPath = "login",
            body = Json.encodeToString(currentUser).encodeToByteArray()
        )?.decodeToString().toString()

    }else{
        println("Incorrect username or password")
    }
}

suspend fun getCurrentUser():String{
    try {
        val currentUser = window.api.tryGet(
            apiPath = "getcurrentuser"
        )?.decodeToString()

        if(currentUser.isNullOrBlank()){
            return ""
        }else{
            return currentUser.replace("\"", "")
        }

    }catch (e:Exception){
        println(e)
        return ""
    }
}

suspend fun logOut(){
    var currentUser = CurrentUser("", "logout")

    window.api.tryPost(
        apiPath = "login",
        body = Json.encodeToString(currentUser).encodeToByteArray()
    )?.decodeToString().toString()
}

suspend fun addTransaction(transaction: Transaction):String{
    return window.api.tryPost(
        apiPath = "addtransaction",
        body = Json.encodeToString(transaction).encodeToByteArray()
    )?.decodeToString().toString()
}

suspend fun getAllTransactions(
    onSuccess: (ApiListResponse)->Unit,
    onError: (Exception)->Unit
){
    try {
        val allTransactions = window.api.tryGet(
            apiPath = "getalltransactions"
        )?.decodeToString()
        if (allTransactions != null){
            onSuccess(Json.decodeFromString(allTransactions))
        }else{
            onError(Exception("Could not get the list of transactions"))
        }
    }catch (e:Exception){
        println(e)
        onError(e)
    }
}

suspend fun addUser(user: User):String{
    return window.api.tryPost(
        apiPath = "adduser",
        body = Json.encodeToString(user).encodeToByteArray()
    )?.decodeToString().toString()
}

suspend fun getBalance():String{
    try {
        val balance = window.api.tryGet(
            apiPath = "getbalance"
        )?.decodeToString()

        if(balance.isNullOrBlank()){
            return "0"
        }else{
            return balance
        }

    }catch (e:Exception){
        println(e)
        return "Error"
    }
}