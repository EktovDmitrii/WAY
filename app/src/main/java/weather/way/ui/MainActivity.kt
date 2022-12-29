package weather.way.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import weather.way.R

class MainActivity : AppCompatActivity() {

private val searchFragment = SearchFragment()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        launchFragment(searchFragment)
    }

    private fun launchFragment(fragment: Fragment){
    supportFragmentManager.beginTransaction().apply {
        replace(R.id.fragment_container_view, fragment)
        addToBackStack(null)
        .commit()
    }
    }
}