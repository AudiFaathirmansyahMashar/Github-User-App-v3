package id.sharekom.myapplication.helpers

import android.database.Cursor
import id.sharekom.myapplication.models.Favorite

object MappingHelper {
    fun mapCursorToArrayList(favoriteCursor: Cursor?) : ArrayList<Favorite>{
        val favoriteList = ArrayList<Favorite>()

        favoriteCursor?.apply {
            while (moveToNext()){
                val id = getLong(getColumnIndexOrThrow("id"))
                val login = getString(getColumnIndexOrThrow("login"))
                val type = getString(getColumnIndexOrThrow("type"))
                val avatarUrl = getString(getColumnIndexOrThrow("avatar_url"))

                favoriteList.add(
                    Favorite(
                        id,
                        login,
                        type,
                        avatarUrl
                    )
                )
            }
        }

        return favoriteList
    }
}