package org.example.kotlinbankwebapp
import kotlinx.serialization.Serializable

@Serializable
data class Transaction(
    val _id:String = "",
    val transactionType:String = "",
    val amount:Double = 0.0,
    val userName:String = ""
)