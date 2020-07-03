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
import id.sharekom.myapplication.viewmodels.FavoriteViewModel
import id.sharekom.myapplication.views.DetailActivity
import kotlinx.android.synthetic.main.list_favorite.view.*

class FavoriteAdapter (private val list: ArrayList<Favorite>) : RecyclerView.Adapter<FavoriteAdapter.FavoriteViewHolder>() {
    inner class FavoriteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteViewHolder = FavoriteViewHolder(LayoutInflater.from(parent.context).inflate(
        R.layout.list_favorite, parent, false))

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: FavoriteViewHolder, position: Int) {
        Glide.with(holder.itemView.context)
            .load(list[position].avatar_url)
            .into(holder.itemView.profile_image)

        holder.itemView.name.text = list[position].login
        holder.itemView.type.text = holder.itemView.resources.getString(R.string.user_type, list[position].type)
        holder.itemView.user_id.text = holder.itemView.resources.getString(R.string.user_id, list[position].id)

        holder.itemView.setOnClickListener{
            val intent = Intent(it.context, DetailActivity::class.java)
            val bundle = Bundle()
            bundle.putString(Values.DATA_MAIN, list[position].login)
            intent.putExtras(bundle)
            ContextCompat.startActivity(it.context, intent, null)
        }

        holder.itemView.btn_hapus.setOnClickListener {
            FavoriteViewModel().deleteData(it.context,
                Favorite(
                    list[position].id,
                    list[position].login,
                    list[position].type,
                    list[position].avatar_url
                )
            )
            list.removeAt(holder.adapterPosition)
            notifyItemRemoved(holder.adapterPosition)
        }
    }
}