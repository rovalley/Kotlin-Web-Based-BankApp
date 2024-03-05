package org.example.kotlinbankwebapp.data

import com.mongodb.kotlin.client.coroutine.MongoClient
import com.varabyte.kobweb.api.data.add
import com.varabyte.kobweb.api.init.InitApi
import com.varabyte.kobweb.api.init.InitApiContext
import kotlinx.coroutines.flow.toList
import org.example.kotlinbankwebapp.Transaction
import org.example.kotlinbankwebapp.model.CurrentUser
import org.example.kotlinbankwebapp.model.User

@InitApi
fun inizializeMongo(context:InitApiContext){
    System.setProperty(
        "org.litote.mongo.test.mapping.service",
        "org.litote.kmongo.serialization.SerializationClassMappingTypeService"
    )
    context.data.add(MongoDbImpl())
}

class MongoDbImpl():MongoDb {
    private val client = MongoClient.create("mongodb+srv://user:KfTsm6ldzFkLTNPg@bankwebapp.y61taid.mongodb.net/?retryWrites=true&w=majority&appName=BankWebApp")
    private val dataBase = client.getDatabase("BankWebApp")
    private val transactionCollection = dataBase.getCollection<Transaction>("transactions")
    private val userCollection = dataBase.getCollection<User>("users")
    private val currentUserCollection = dataBase.getCollection<CurrentUser>("currentUser")


    override suspend fun getAllTransactions(): List<Transaction> {
        return transactionCollection.find().toList()
    }

    override suspend fun addTransaction(transaction: Transaction): Boolean {
        return transactionCollection.insertOne(transaction).wasAcknowledged()
    }

    override suspend fun addUser(user: User): Boolean {
        return userCollection.insertOne(user).wasAcknowledged()
    }

    override suspend fun getUsers(): List<User> {
        return userCollection.find().toList()
    }

    override suspend fun getCurrentUser(): List<CurrentUser> {
        return currentUserCollection.find().toList()
    }

    override suspend fun addCurrentUser(currentUser: CurrentUser): Boolean {
        return currentUserCollection.insertOne(currentUser).wasAcknowledged()
    }

}