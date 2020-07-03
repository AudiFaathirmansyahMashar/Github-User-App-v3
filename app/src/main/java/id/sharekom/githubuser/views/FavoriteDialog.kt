package id.sharekom.githubuser.views

import android.content.Context
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import id.sharekom.githubuser.R
import id.sharekom.githubuser.models.favorite.Favorite
import id.sharekom.githubuser.viewmodels.FavoriteViewModel

class FavoriteDialog {
    companion object{
        fun choiceDialog(context: Context, favorite: Favorite):AlertDialog.Builder{
            return AlertDialog.Builder(context)
                .setTitle(context.resources.getString(R.string.dialog_title))
                .setMessage(context.resources.getString(R.string.dialog_message))
                .setPositiveButton(context.resources.getString(R.string.dialog_positive)){ _, _ ->
                    val favoriteDialog= FavoriteViewModel().insertData(context, favorite) ?: -1
                    if (favoriteDialog > 0) Toast.makeText(context, context.resources.getString(R.string.successfully_added), Toast.LENGTH_SHORT).show()
                    else Toast.makeText(context, context.resources.getString(R.string.something_wrong), Toast.LENGTH_SHORT).show()
                }
                .setNegativeButton(context.resources.getString(R.string.dialog_negative)){ _, _ ->
                    Toast.makeText(context, context.resources.getString(R.string.failed_added), Toast.LENGTH_SHORT).show()
                }
                .setCancelable(false)
        }
    }
}
