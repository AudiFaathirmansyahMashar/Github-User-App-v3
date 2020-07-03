package id.sharekom.githubuser.provider

import android.content.ContentProvider
import android.content.ContentValues
import android.content.Context
import android.content.UriMatcher
import android.database.Cursor
import android.net.Uri
import id.sharekom.githubuser.helpers.DatabaseContract
import id.sharekom.githubuser.helpers.Databases
import id.sharekom.githubuser.localdatabase.FavoriteDao
import id.sharekom.githubuser.models.favorite.Favorite

class FavoriteProvider : ContentProvider() {

    companion object {
        private const val FAVORITE = 1
        private const val FAVORITE_ID = 2
        private lateinit var favoriteDao: FavoriteDao

        private val sUriMatcher = UriMatcher(UriMatcher.NO_MATCH)

        init {
            sUriMatcher.addURI(
                DatabaseContract.AUTHORITY,
                DatabaseContract.NoteColumns.TABLE_NAME,
                FAVORITE
            )

            sUriMatcher.addURI(
                DatabaseContract.AUTHORITY,
                "${DatabaseContract.NoteColumns.TABLE_NAME}/#",
                FAVORITE_ID
            )
        }
    }

    override fun delete(uri: Uri, selection: String?, selectionArgs: Array<String>?): Int {

        context?.contentResolver?.notifyChange(DatabaseContract.NoteColumns.CONTENT_URI, null)

        return favoriteDao.delete(uri.lastPathSegment.toString().toLong())
    }

    override fun getType(uri: Uri): String? {
        TODO(
            "Implement this to handle requests for the MIME type of the data" +
                    "at the given URI"
        )
    }

    override fun insert(uri: Uri, values: ContentValues?): Uri? {
        val added: Long = when (FAVORITE) {
            sUriMatcher.match(uri) -> favoriteDao.insertAll(
                Favorite(
                    values?.getAsLong(
                        DatabaseContract.NoteColumns.ID
                    )!!,
                    values.getAsString(DatabaseContract.NoteColumns.LOGIN),
                    values.getAsString(DatabaseContract.NoteColumns.TYPE),
                    values.getAsString(DatabaseContract.NoteColumns.AVATAR_URL)
                )
            )
            else -> 0
        }

        context?.contentResolver?.notifyChange(DatabaseContract.NoteColumns.CONTENT_URI, null)

        return Uri.parse("${DatabaseContract.NoteColumns.CONTENT_URI}/$added")
    }

    override fun onCreate(): Boolean {
        favoriteDao = Databases.getFavoriteDatabase(context as Context)!!.favoriteDao()
        return true
    }

    override fun query(
        uri: Uri, projection: Array<String>?, selection: String?,
        selectionArgs: Array<String>?, sortOrder: String?
    ): Cursor? {
        return if (sUriMatcher.match(uri) == FAVORITE) favoriteDao.getAllCursor() else null
    }

    override fun update(
        uri: Uri, values: ContentValues?, selection: String?,
        selectionArgs: Array<String>?
    ): Int {
        TODO("Implement this to handle requests to update one or more rows.")
    }
}
