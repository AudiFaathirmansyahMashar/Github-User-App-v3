package id.sharekom.githubuser.helpers

import android.content.Context
import androidx.room.Room
import id.sharekom.githubuser.localdatabase.FavoriteDatabase

object Databases {
    fun getFavoriteDatabase(context: Context): FavoriteDatabase? {
        return Room.databaseBuilder(
            context,
            FavoriteDatabase::class.java, "favorite"
        ).allowMainThreadQueries().build()
    }
}