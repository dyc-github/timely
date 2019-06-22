package timely.com.timely.helpers

import android.content.Context
import android.content.Intent

class ActivityLauncher(private val context: Context) : ActivityLauncherInterface {
    override fun <T> startActivity(activityClass: Class<T>) {
        val intent = Intent(context, activityClass)
        context.startActivity(intent)
    }
}

interface ActivityLauncherInterface {
    fun <T> startActivity(activityClass: Class<T>)
}