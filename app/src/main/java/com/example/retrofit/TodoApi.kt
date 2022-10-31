package com.example.retrofit

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

//we specify the api types here

interface TodoApi {

    //we get list of "todo" data types as response
    @GET("/todos")  //what comes after the ".com" in the base url address
    suspend fun getTodos(
//        @Query("key") key: String
    ): Response<List<Todo>>

    //if we want to post to the server
//    @POST("*/createTodo")
//    fun createTodo(
////        @Body todo: Todo
//    ): Response<CreateTodo>

}