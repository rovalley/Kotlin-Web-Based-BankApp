package org.example.kotlinbankwebapp.data

import org.example.kotlinbankwebapp.Transaction
import org.example.kotlinbankwebapp.model.CurrentUser
import org.example.kotlinbankwebapp.model.User

interface MongoDb {
    suspend fun getAllTransactions():List<Transaction>
    suspend fun addTransaction(transaction:Transaction):Boolean

    suspend fun addUser(user: User):Boolean
    suspend fun getUsers():List<User>
    suspend fun getCurrentUser():List<CurrentUser>

    suspend fun addCurrentUser(currentUser: CurrentUser):Boolean

}