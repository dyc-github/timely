package timely.com.timely.activities

import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import timely.com.timely.R
import timely.com.timely.bindView

class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController
    val bottomNavigationView: BottomNavigationView by bindView(R.id.bottom_navigation_view)

    private val onNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_profile -> {
                navController.navigate(R.id.profileFragment)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_feed -> {
                navController.navigate(R.id.feedFragment)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_messages -> {
                navController.navigate(R.id.messagesFragment)
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        navController = findNavController(R.id.main_nav_fragment)

        bottomNavigationView.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener)
        bottomNavigationView.selectedItemId = R.id.navigation_feed
    }

    override fun onSupportNavigateUp(): Boolean = navController.navigateUp()
}
