package id.sharekom.myapplication.networking

import id.sharekom.myapplication.helpers.Values
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    val retrofit:Retrofit = Retrofit.Builder()
        .baseUrl(Values.MAIN_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}