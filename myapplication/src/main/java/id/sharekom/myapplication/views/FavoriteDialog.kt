package id.sharekom.myapplication.views

import android.content.Context
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import id.sharekom.myapplication.R
import id.sharekom.myapplication.models.Favorite
import id.sharekom.myapplication.viewmodels.FavoriteViewModel

class FavoriteDialog {
    companion object {
        fun choiceDialog(context: Context, favorite: Favorite): AlertDialog.Builder {
            return AlertDialog.Builder(context)
                .setTitle(context.resources.getString(R.string.dialog_title))
                .setMessage(context.resources.getString(R.string.dialog_message))
                .setPositiveButton(context.resources.getString(R.string.dialog_positive)) { _, _ ->
                    val favoriteDialog = FavoriteViewModel().insertData(context, favorite)!!

                    if (favoriteDialog > 0) Toast.makeText(
                        context,
                        context.resources.getString(R.string.successfully_added),
                        Toast.LENGTH_SHORT
                    ).show() else Toast.makeText(
                        context,
                        context.resources.getString(R.string.something_wrong),
                        Toast.LENGTH_SHORT
                    ).show()
                }
                .setNegativeButton(context.resources.getString(R.string.dialog_negative)) { _, _ ->
                    Toast.makeText(
                        context,
                        context.resources.getString(R.string.failed_added),
                        Toast.LENGTH_SHORT
                    ).show()
                }
                .setCancelable(false)
        }
    }
}
