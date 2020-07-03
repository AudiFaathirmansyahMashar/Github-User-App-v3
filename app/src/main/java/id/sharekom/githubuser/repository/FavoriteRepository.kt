package id.sharekom.githubuser.repository

import android.content.Context
import androidx.lifecycle.MutableLiveData
import id.sharekom.githubuser.helpers.Databases
import id.sharekom.githubuser.helpers.MappingHelper
import id.sharekom.githubuser.models.favorite.Favorite
import java.lang.Exception

class FavoriteRepository {
    companion object{
        private lateinit var data:MutableLiveData<ArrayList<Favorite>>

        fun insert(context: Context, favorite:Favorite): Long? {
            try {
                return Databases.getFavoriteDatabase(context)?.favoriteDao()?.insertAll(favorite)
            }catch (e:Exception){
                return -1
            }
        }

        fun delete(context: Context, favorite: Favorite){
            Databases.getFavoriteDatabase(context)?.favoriteDao()?.delete(favorite.id)
        }

        fun getAllData(context: Context):MutableLiveData<ArrayList<Favorite>>{
            data = MutableLiveData()
            data.value = MappingHelper.mapCursorToArrayList(Databases.getFavoriteDatabase(context)?.favoriteDao()?.getAllCursor())
            return data
        }
    }
}