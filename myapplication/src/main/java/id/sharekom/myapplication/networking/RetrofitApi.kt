package id.sharekom.myapplication.networking

import id.sharekom.myapplication.helpers.Profile
import id.sharekom.myapplication.models.SearchDataItem
import retrofit2.Call
import retrofit2.http.*

interface RetrofitApi {
    @GET("users/{username}")
    fun getProfile(@Path("username") username: String, @Header("Authorization") token:String):Call<Profile>

    @GET("users/{username}/followers")
    fun getFollowers(@Path("username") username: String, @Header("Authorization") token:String):Call<List<SearchDataItem>>

    @GET("users/{username}/following")
    fun getFollowing(@Path("username") username: String, @Header("Authorization") token:String):Call<List<SearchDataItem>>
}