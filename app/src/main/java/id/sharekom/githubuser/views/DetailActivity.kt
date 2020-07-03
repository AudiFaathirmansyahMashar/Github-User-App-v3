package id.sharekom.githubuser.views

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.bumptech.glide.Glide
import id.sharekom.githubuser.R
import id.sharekom.githubuser.helpers.Values
import id.sharekom.githubuser.viewmodels.ProfileViewModel
import id.sharekom.githubuser.views.fragments.FollowersFragment
import id.sharekom.githubuser.views.fragments.FollowingFragment
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity() {
    private lateinit var viewModel: ProfileViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        profile_viewpager.adapter =
            ScreenSlidePagerAdapter(
                this
            )
        profile_viewpager.isUserInputEnabled = false

        btn_favorite.setOnClickListener {
            startActivity(Intent(this, FavoriteActivity::class.java))
        }

        val username = intent.getStringExtra(Values.DATA_MAIN).toString()
        viewModel = ViewModelProvider(this).get(ProfileViewModel::class.java)
        viewModel.setProfile(username)
        viewModel.getProfile().observe(this, Observer {
            Glide.with(this)
                .load(it.avatar_url)
                .into(profile_image)

            name.text = it.name
            location.text = resources.getString(R.string.location_username, it.location, it.login)
            followers_following.text = resources.getString(R.string.count_company, it.followers, it.following, it.public_repos, it.company)
            id_type.text = resources.getString(R.string.id_type, it.id, it.type)

            detail_data.visibility = View.VISIBLE
            profile_progress.visibility = View.INVISIBLE
        })

        detail_bottomnavbar.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.followers ->  {
                    profile_viewpager.currentItem = 0
                }
                R.id.following ->  {
                    profile_viewpager.currentItem = 1
                }
            }

            return@setOnNavigationItemSelectedListener true
        }
    }

    private class ScreenSlidePagerAdapter(fa: FragmentActivity?) :
        FragmentStateAdapter(fa!!) {
        override fun createFragment(position: Int): Fragment {
            return when (position) {
                0 -> FollowersFragment()
                1 -> FollowingFragment()
                else -> FollowersFragment()
            }
        }

        override fun getItemCount(): Int {
            return 2
        }
    }
}