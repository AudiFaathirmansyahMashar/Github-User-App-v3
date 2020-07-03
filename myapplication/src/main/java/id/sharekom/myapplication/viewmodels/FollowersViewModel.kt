package id.sharekom.myapplication.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import id.sharekom.myapplication.repository.FollowersRepository
import id.sharekom.myapplication.models.SearchDataItem

class FollowersViewModel : ViewModel(){
    private var data: MutableLiveData<List<SearchDataItem>> = MutableLiveData()

    fun setFollowers(username:String){
        FollowersRepository.init(username)
        data =
            FollowersRepository.getFollowers()
    }

    fun getFollowers(): LiveData<List<SearchDataItem>> {
        return data
    }
}