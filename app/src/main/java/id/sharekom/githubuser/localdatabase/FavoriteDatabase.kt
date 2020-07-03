package id.sharekom.githubuser.localdatabase

import androidx.room.Database
import androidx.room.RoomDatabase
import id.sharekom.githubuser.models.favorite.Favorite

@Database(entities = [Favorite::class], version = 1)
abstract class FavoriteDatabase : RoomDatabase() {
    abstract fun favoriteDao():FavoriteDao
}