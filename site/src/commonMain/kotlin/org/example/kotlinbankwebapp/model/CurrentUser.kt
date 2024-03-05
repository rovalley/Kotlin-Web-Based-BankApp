package org.example.kotlinbankwebapp.model

import kotlinx.serialization.Serializable

@Serializable
data class CurrentUser(
    val _id:String = "",
    val username:String
)
