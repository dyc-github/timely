package timely.com.timely.helpers

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import javax.inject.Inject

class ActivityLauncherImpl @Inject constructor(private val activity: AppCompatActivity) : ActivityLauncher {
    override fun <T> startActivity(activityClass: Class<T>) {
        val intent = Intent(activity, activityClass)
        activity.startActivity(intent)
    }
}

interface ActivityLauncher {
    fun <T> startActivity(activityClass: Class<T>)
}