package timely.com.timely

import android.app.Application
import androidx.appcompat.app.AppCompatActivity
import timely.com.timely.dagger.AppComponent
import timely.com.timely.dagger.AppModule
import timely.com.timely.dagger.DaggerAppComponent

class TimelyApplication : Application() {
    lateinit var component: AppComponent

    fun setComponent(activity: AppCompatActivity) {
        component = DaggerAppComponent.builder().appModule(AppModule(activity)).build()
    }
}