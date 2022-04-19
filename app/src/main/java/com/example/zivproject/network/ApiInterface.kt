package com.example.zivproject.network

import com.example.zivproject.data.MyResponse
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface ApiInterface {


    @GET("/nancymadan/assignment/db")
    suspend fun getData(): Response<MyResponse>

    companion object{


        var BASE_URL = "https://my-json-server.typicode.com"


        fun create():ApiInterface{

            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build()

            return retrofit.create(ApiInterface::class.java)

        }
    }
}