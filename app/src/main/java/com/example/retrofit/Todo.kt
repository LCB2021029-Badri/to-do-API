package com.example.retrofit

data class Todo(val completed: Boolean, val id: Int, val title: String, val userId: Int)
// data type of the json file we are using as output from "TodoApi"
// to parse json response