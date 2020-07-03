package id.sharekom.myapplication.views

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import id.sharekom.myapplication.views.adapters.FavoriteAdapter
import id.sharekom.myapplication.viewmodels.FavoriteViewModel
import id.sharekom.myapplication.R
import kotlinx.android.synthetic.main.activity_favorite.*

class FavoriteActivity : AppCompatActivity() {
    private lateinit var viewModel:FavoriteViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favorite)

        supportActionBar?.title = resources.getString(R.string.favorite_title)

        favorite_recycler.setHasFixedSize(true)

        viewModel = ViewModelProvider(this).get(FavoriteViewModel::class.java)
        getAllData()
    }

    private fun getAllData(){
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
        if (item.itemId == R.id.change_language){
            startActivity(Intent(Settings.ACTION_LOCALE_SETTINGS))
        }

        return super.onOptionsItemSelected(item)
    }

    override fun onResume() {
        super.onResume()
        getAllData()
    }
}