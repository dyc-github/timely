package timely.com.timely.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main.*
import timely.com.timely.R

class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController

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

        bottom_navigation_view.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener)
        bottom_navigation_view.selectedItemId = R.id.navigation_feed
    }

    override fun onSupportNavigateUp(): Boolean = navController.navigateUp()
}
