package id.sharekom.myapplication.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import id.sharekom.myapplication.repository.FollowingRepository
import id.sharekom.myapplication.models.SearchDataItem

class FollowingViewModel : ViewModel() {
    private var data: MutableLiveData<List<SearchDataItem>> = MutableLiveData()

    fun setFollowing(username:String){
        FollowingRepository.init(username)
        data =
            FollowingRepository.getFollowing()
    }

    fun getFollowing(): LiveData<List<SearchDataItem>> {
        return data
    }
}