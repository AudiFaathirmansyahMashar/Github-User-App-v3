package id.sharekom.githubuser.views

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import id.sharekom.githubuser.R
import id.sharekom.githubuser.viewmodels.FavoriteViewModel
import id.sharekom.githubuser.views.adapters.FavoriteAdapter
import kotlinx.android.synthetic.main.activity_favorite.*

class FavoriteActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favorite)

        supportActionBar?.title = resources.getString(R.string.favorite_title)

        favorite_recycler.setHasFixedSize(true)

        val viewModel = ViewModelProvider(this).get(FavoriteViewModel::class.java)

        viewModel.getAllData(this).observe(this, Observer {
            if (it == null) Toast.makeText(this, resources.getString(R.string.failed_to_show), Toast.LENGTH_SHORT).show()
            else favorite_recycler.adapter = FavoriteAdapter(it)
        })
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.favorite_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId){
            R.id.change_language -> (Intent(Settings.ACTION_LOCALE_SETTINGS))
            R.id.settings -> startActivity(Intent(this, SettingsActivity::class.java))
        }

        return super.onOptionsItemSelected(item)
    }
}