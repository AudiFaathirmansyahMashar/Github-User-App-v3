package id.sharekom.myapplication.repository

import android.content.ContentValues
import android.content.Context
import android.net.Uri
import androidx.core.content.contentValuesOf
import androidx.lifecycle.MutableLiveData
import id.sharekom.myapplication.helpers.DatabaseContract
import id.sharekom.myapplication.helpers.MappingHelper
import id.sharekom.myapplication.models.Favorite
import java.lang.Exception

class FavoriteRepository {
    companion object{
        private lateinit var data:MutableLiveData<ArrayList<Favorite>>

        fun insert(context: Context, favorite: Favorite): Long{
            try {
                val values = contentValuesOf()
                values.put(DatabaseContract.NoteColumns.ID, favorite.id)
                values.put(DatabaseContract.NoteColumns.LOGIN, favorite.login)
                values.put(DatabaseContract.NoteColumns.TYPE, favorite.type)
                values.put(DatabaseContract.NoteColumns.AVATAR_URL, favorite.avatar_url)

                context.contentResolver.insert(DatabaseContract.NoteColumns.CONTENT_URI, values)

                return favorite.id
            }catch (e:Exception){
                return -1
            }
        }

        fun delete(context: Context, favorite: Favorite){
            val uriWithId = Uri.parse(DatabaseContract.NoteColumns.CONTENT_URI.toString() + "/" + favorite.id)
            val values = ContentValues()
            values.put(DatabaseContract.NoteColumns.LOGIN, favorite.login)
            values.put(DatabaseContract.NoteColumns.TYPE, favorite.type)
            values.put(DatabaseContract.NoteColumns.AVATAR_URL, favorite.avatar_url)
            context.contentResolver.delete(uriWithId, null, null)
        }

        fun getAllData(context: Context):MutableLiveData<ArrayList<Favorite>>{
            data = MutableLiveData()
            data.value = MappingHelper.mapCursorToArrayList(context.contentResolver.query(DatabaseContract.NoteColumns.CONTENT_URI, null, null, null, null))
            return data
        }
    }
}