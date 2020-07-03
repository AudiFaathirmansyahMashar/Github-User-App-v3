package id.sharekom.myapplication.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import id.sharekom.myapplication.helpers.Profile
import id.sharekom.myapplication.helpers.Values
import id.sharekom.myapplication.networking.RetrofitApi
import id.sharekom.myapplication.networking.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProfileRepository {
    companion object{
        private val data:MutableLiveData<Profile> = MutableLiveData()

        fun init(username: String){
            val api: RetrofitApi = RetrofitClient.retrofit.create(RetrofitApi::class.java)

            val call: Call<Profile> = api.getProfile(username, Values.TOKEN)

            call.enqueue(object : Callback<Profile>{
                override fun onFailure(call: Call<Profile>, t: Throwable) {
                    Log.e("Profile", t.message.toString())
                }

                override fun onResponse(call: Call<Profile>, response: Response<Profile>) {
                    data.value = response.body()
                }

            })
        }

        fun getProfile():MutableLiveData<Profile>{
            return data
        }
    }
}