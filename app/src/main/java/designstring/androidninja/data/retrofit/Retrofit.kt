package designstring.androidninja.data.retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private val BASE_URL = "https://reqres.in/api/"

private val retrofitInstance = Retrofit.Builder()
    .baseUrl(BASE_URL)
    .addConverterFactory(GsonConverterFactory.create())
    .build()

// Implementation of RetrofitInterface
val retrofitImpl = retrofitInstance.create(RetrofitInterface::class.java)