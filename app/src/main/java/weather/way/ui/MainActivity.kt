package weather.way.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import weather.way.R
import weather.way.ui.start.StartFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        launchSearchFragment()
    }

    private fun launchSearchFragment() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container_view, StartFragment.newInstance())
            .addToBackStack(null)
            .commit()
    }
}