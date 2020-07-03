package id.sharekom.githubuser.views

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.widget.RemoteViews
import android.widget.RemoteViewsService
import androidx.core.os.bundleOf
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import id.sharekom.githubuser.R
import id.sharekom.githubuser.helpers.Databases
import id.sharekom.githubuser.helpers.MappingHelper
import id.sharekom.githubuser.models.favorite.Favorite
import id.sharekom.githubuser.viewmodels.FavoriteViewModel
import java.io.InputStream
import java.net.URL
import kotlin.collections.ArrayList

internal class StackRemoteViewsFactory(private val mContext: Context) : RemoteViewsService.RemoteViewsFactory {

    private val mWidgetItems = ArrayList<Bitmap>()
    private val login = ArrayList<String>()

    override fun onCreate() {}

    override fun onDataSetChanged() {
        val list = MappingHelper.mapCursorToArrayList(Databases.getFavoriteDatabase(mContext)?.favoriteDao()?.getAllCursor())

        list.forEach {
            mWidgetItems.add(BitmapFactory.decodeStream(URL(it.avatar_url).getContent() as InputStream))
            login.add(it.login.toString())
        }
    }

    override fun onDestroy() {}

    override fun getCount(): Int = mWidgetItems.size

    override fun getViewAt(position: Int): RemoteViews {
        val rv = RemoteViews(mContext.packageName, R.layout.widget_item)

        rv.setImageViewBitmap(R.id.imageView, mWidgetItems[position])

        val extras = bundleOf(
            ImageBannerWidget.EXTRA_ITEM to position
        )
        val fillInIntent = Intent()
        fillInIntent.putExtras(extras)

        rv.setOnClickFillInIntent(R.id.imageView, fillInIntent)
        return rv
    }

    override fun getLoadingView(): RemoteViews? = null

    override fun getViewTypeCount(): Int = 1

    override fun getItemId(i: Int): Long = 0

    override fun hasStableIds(): Boolean = false

}