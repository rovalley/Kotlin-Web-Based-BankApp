package org.example.kotlinbankwebapp.api

import com.varabyte.kobweb.api.Api
import com.varabyte.kobweb.api.ApiContext
import com.varabyte.kobweb.api.data.getValue
import com.varabyte.kobweb.api.http.setBodyText
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.bson.codecs.ObjectIdGenerator
import org.example.kotlinbankwebapp.data.MongoDbImpl
import org.example.kotlinbankwebapp.model.CurrentUser
import org.example.kotlinbankwebapp.model.User

@Api(routeOverride="adduser")
suspend fun addUser(context: ApiContext){
    try {
        val user = context.req.body?.decodeToString()?.let { Json.decodeFromString<User>(it) }
        val newUser = user?.copy(_id = ObjectIdGenerator().generate().toString())
        var responseBody = Json.encodeToString(newUser?.let { context.data.getValue<MongoDbImpl>().addUser(it) })
        context.res.setBodyText(responseBody)
    } catch (e:Exception){
        context.res.setBodyText(Json.encodeToString(e))
    }
}

@Api(routeOverride = "getusers")
suspend fun getUsers(context: ApiContext){
    try {
        val users = context.data.getValue<MongoDbImpl>().getUsers()
        context.res.setBodyText(Json.encodeToString(users))
    }catch (e:Exception){
        context.res.setBodyText(Json.encodeToString(e))
    }
}

@Api(routeOverride = "login")
suspend fun login(context: ApiContext){
    try {
        val currentUser = context.req.body?.decodeToString()?.let { Json.decodeFromString<CurrentUser>(it) }
        val newCurrentUser = currentUser?.copy(_id = ObjectIdGenerator().generate().toString())
        var responseBody = Json.encodeToString(newCurrentUser?.let { context.data.getValue<MongoDbImpl>().addCurrentUser(it) })
        context.res.setBodyText(responseBody)
    }catch (e:Exception){
        context.res.setBodyText(Json.encodeToString(e))
    }
}

@Api(routeOverride ="getcurrentuser")
suspend fun getCurrentUser(context: ApiContext){
    try {
        val allCurrentUser = context.data.getValue<MongoDbImpl>().getCurrentUser()

        var currentUser = ""
        for (user in allCurrentUser){
            currentUser = user.username
        }
        var responseBody = Json.encodeToString(currentUser)
        context.res.setBodyText(responseBody)

    }catch (e:Exception){
        context.res.setBodyText(Json.encodeToString(e))
    }
}