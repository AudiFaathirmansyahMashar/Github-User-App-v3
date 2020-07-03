package id.sharekom.githubuser.models.favorite

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Favorite (@PrimaryKey val id:Long, val login:String?, val type:String?, val avatar_url:String)