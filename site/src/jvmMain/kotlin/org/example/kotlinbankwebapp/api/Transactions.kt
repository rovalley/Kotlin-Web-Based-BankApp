package org.example.kotlinbankwebapp.api

import com.varabyte.kobweb.api.Api
import com.varabyte.kobweb.api.ApiContext
import com.varabyte.kobweb.api.data.getValue
import com.varabyte.kobweb.api.http.setBodyText
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.bson.codecs.ObjectIdGenerator
import org.example.kotlinbankwebapp.Transaction
import org.example.kotlinbankwebapp.data.MongoDbImpl
import org.example.kotlinbankwebapp.model.ApiListResponse

@Api(routeOverride="addtransaction")
suspend fun addTransaction(context:ApiContext){
    try {
        val transaction = context.req.body?.decodeToString()?.let { Json.decodeFromString<Transaction>(it) }
        val newTransaction = transaction?.copy(_id = ObjectIdGenerator().generate().toString())
        var responseBody = Json.encodeToString(newTransaction?.let { context.data.getValue<MongoDbImpl>().addTransaction(it) })
        context.res.setBodyText(responseBody)
    } catch (e:Exception){
        context.res.setBodyText(Json.encodeToString(e))
    }
}

@Api(routeOverride ="getalltransactions")
suspend fun getAllTransactions(context: ApiContext){
    try {
        val allTransactions = context.data.getValue<MongoDbImpl>().getAllTransactions()
        context.res.setBodyText(Json.encodeToString(ApiListResponse.Success(data = allTransactions)))
    }catch (e:Exception){
        context.res.setBodyText(Json.encodeToString(ApiListResponse.Error(message=e.message.toString())))
    }
}

@Api(routeOverride ="getbalance")
suspend fun getBalance(context: ApiContext){
    try {

        val allCurrentUser = context.data.getValue<MongoDbImpl>().getCurrentUser()
        var currentUser = ""
        for (user in allCurrentUser){
            currentUser = user.username
        }

        val allTransactions = context.data.getValue<MongoDbImpl>().getAllTransactions()

        var balance = 0.0
        for (transaction in allTransactions){

            if (transaction.userName.equals(currentUser)) {

                if (transaction.transactionType.equals("Deposit")) {
                    balance += transaction.amount
                } else if (transaction.transactionType.equals("Withdraw")) {
                    balance -= transaction.amount
                }

            }
        }
        var responseBody = Json.encodeToString(balance)
        context.res.setBodyText(responseBody)

    }catch (e:Exception){
        context.res.setBodyText(Json.encodeToString(e))
    }
}