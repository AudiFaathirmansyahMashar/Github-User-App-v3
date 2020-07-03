package id.sharekom.myapplication.views.adapters

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import id.sharekom.myapplication.R
import id.sharekom.myapplication.helpers.Values
import id.sharekom.myapplication.models.Favorite
import id.sharekom.myapplication.models.SearchDataItem
import id.sharekom.myapplication.views.DetailActivity
import id.sharekom.myapplication.views.FavoriteDialog
import kotlinx.android.synthetic.main.list_main.view.*

class FollowersAdapter(private val list: List<SearchDataItem>) : RecyclerView.Adapter<FollowersAdapter.FollowersViewHolder>() {
    inner class FollowersViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FollowersViewHolder = FollowersViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.list_main, parent, false))

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: FollowersViewHolder, position: Int) {
        Glide.with(holder.itemView.context)
            .load(list[position].avatar_url)
            .into(holder.itemView.profile_image)

        holder.itemView.name.text = list[position].login
        holder.itemView.type.text = holder.itemView.resources.getString(R.string.user_type, list[position].type)
        holder.itemView.user_id.text = holder.itemView.resources.getString(R.string.user_id, list[position].id)

        holder.itemView.setOnLongClickListener {
            FavoriteDialog.choiceDialog(holder.itemView.context,
                Favorite(
                    list[position].id,
                    list[position].login,
                    list[position].type,
                    list[position].avatar_url
                )
            ).show()

            return@setOnLongClickListener true
        }

        holder.itemView.setOnClickListener{
            val intent = Intent(it.context, DetailActivity::class.java)
            val bundle = Bundle()
            bundle.putString(Values.DATA_MAIN, list[position].login)
            intent.putExtras(bundle)
            ContextCompat.startActivity(it.context, intent, null)
        }
    }
}