package id.sharekom.githubuser.localdatabase

import android.database.Cursor
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import id.sharekom.githubuser.models.favorite.Favorite

@Dao
interface FavoriteDao {
    @Query("SELECT * FROM favorite")
    fun getAllCursor():Cursor

    @Insert
    fun insertAll(favorite: Favorite):Long

    @Query("DELETE FROM favorite WHERE id = :id")
    fun delete(id:Long):Int
}