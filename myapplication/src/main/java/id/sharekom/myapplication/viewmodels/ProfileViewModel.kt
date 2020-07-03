package id.sharekom.myapplication.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import id.sharekom.myapplication.repository.ProfileRepository
import id.sharekom.myapplication.helpers.Profile

class ProfileViewModel : ViewModel() {
    private var data:MutableLiveData<Profile> = MutableLiveData()

    fun setProfile(username:String){
        ProfileRepository.init(username)
        data = ProfileRepository.getProfile()
    }

    fun getProfile():LiveData<Profile>{
        return data
    }
}