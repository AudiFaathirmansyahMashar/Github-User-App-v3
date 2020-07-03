package id.sharekom.myapplication.viewmodels

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import id.sharekom.myapplication.repository.FavoriteRepository
import id.sharekom.myapplication.models.Favorite

class FavoriteViewModel : ViewModel() {

    fun insertData(context: Context, favorite: Favorite) : Long?{
        return FavoriteRepository.insert(
            context,
            favorite
        )
    }

    fun deleteData(context: Context, favorite: Favorite){
        FavoriteRepository.delete(
            context,
            favorite
        )
    }

    fun getAllData(context: Context):LiveData<ArrayList<Favorite>>{
        return FavoriteRepository.getAllData(
            context
        )
    }
}