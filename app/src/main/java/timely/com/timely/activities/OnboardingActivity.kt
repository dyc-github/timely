package timely.com.timely.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import timely.com.timely.R
import timely.com.timely.TimelyApplication

class OnboardingActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_onboarding)
        (application as TimelyApplication).setComponent(this)
    }
}
