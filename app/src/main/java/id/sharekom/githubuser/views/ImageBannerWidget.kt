package id.sharekom.githubuser.views

import android.app.PendingIntent
import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.RemoteViews
import androidx.core.content.ContextCompat
import androidx.core.net.toUri
import id.sharekom.githubuser.R
import id.sharekom.githubuser.helpers.Databases
import id.sharekom.githubuser.helpers.MappingHelper
import id.sharekom.githubuser.helpers.Values
import id.sharekom.githubuser.models.favorite.Favorite

class ImageBannerWidget : AppWidgetProvider() {

    companion object {
        lateinit var list:ArrayList<Favorite>
        private const val TOAST_ACTION = "id.sharekom.TOAST_ACTION"
        const val EXTRA_ITEM = "id.sharekom.EXTRA_ITEM"

        private fun updateAppWidget(context: Context, appWidgetManager: AppWidgetManager, appWidgetId: Int) {
            val intent = Intent(context, StackWidgetService::class.java)
            intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetId)
            intent.data = intent.toUri(Intent.URI_INTENT_SCHEME).toUri()

            val views = RemoteViews(context.packageName, R.layout.image_banner_widget)
            views.setRemoteAdapter(R.id.stack_view, intent)
            views.setEmptyView(R.id.stack_view, R.id.empty_view)

            val toastIntent = Intent(context, ImageBannerWidget::class.java)
            toastIntent.action = TOAST_ACTION
            toastIntent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetId)
            intent.data = intent.toUri(Intent.URI_INTENT_SCHEME).toUri()
            val toastPendingIntent = PendingIntent.getBroadcast(context, 0, toastIntent, PendingIntent.FLAG_UPDATE_CURRENT)
            views.setPendingIntentTemplate(R.id.stack_view, toastPendingIntent)

            appWidgetManager.updateAppWidget(appWidgetId, views)
        }
    }

    override fun onUpdate(context: Context, appWidgetManager: AppWidgetManager, appWidgetIds: IntArray) {
        for (appWidgetId in appWidgetIds) {
            list = MappingHelper.mapCursorToArrayList(Databases.getFavoriteDatabase(context)?.favoriteDao()?.getAllCursor())
            updateAppWidget(context, appWidgetManager, appWidgetId)
        }
    }

    override fun onReceive(context: Context, intent: Intent) {
        super.onReceive(context, intent)
        if (intent.action != null) {
            if (intent.action == TOAST_ACTION) {
                val viewIndex = intent.getIntExtra(EXTRA_ITEM, 0)

                val favoriteIntent = Intent(context, DetailActivity::class.java)
                val bundle = Bundle()
                bundle.putString(Values.DATA_MAIN, list[viewIndex].login)
                favoriteIntent.putExtras(bundle)
                ContextCompat.startActivity(context, favoriteIntent, null)
            }
        }
    }
}

