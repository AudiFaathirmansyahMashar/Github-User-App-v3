package id.sharekom.myapplication.helpers

import android.net.Uri
import android.provider.BaseColumns

object DatabaseContract {

    const val AUTHORITY = "id.sharekom.githubuser"
    const val SCHEME = "content"

    class NoteColumns : BaseColumns {

        companion object {
            const val TABLE_NAME = "favorite"
            const val ID = "id"
            const val LOGIN = "login"
            const val TYPE = "type"
            const val AVATAR_URL = "avatar_url"

            // untuk membuat URI content://com.dicoding.picodiploma.mynotesapp/note
            val CONTENT_URI: Uri = Uri.Builder().scheme(SCHEME)
                .authority(AUTHORITY)
                .appendPath(TABLE_NAME)
                .build()
        }

    }
}