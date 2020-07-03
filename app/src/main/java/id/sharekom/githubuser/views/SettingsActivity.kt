package id.sharekom.githubuser.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import id.sharekom.githubuser.R
import id.sharekom.githubuser.helpers.AlarmReceiver
import kotlinx.android.synthetic.main.activity_settings.*

class SettingsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        val alarmReceiver = AlarmReceiver()

        active.setOnClickListener {
            alarmReceiver.setRepeatingAlarm(this, "09:00")
            Toast.makeText(this, resources.getString(R.string.turn_on), Toast.LENGTH_SHORT).show()
        }

        non_active.setOnClickListener{
            alarmReceiver.cancelAlarm(this)
            Toast.makeText(this, resources.getString(R.string.turn_off), Toast.LENGTH_SHORT).show()
        }
    }
}