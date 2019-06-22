package timely.com.timely.vms

import androidx.lifecycle.ViewModel
import timely.com.timely.activities.MainActivity
import timely.com.timely.helpers.ActivityLauncherInterface
import timely.com.timely.helpers.FirebaseAuthenticationHelperInterface

class SignUpFragmentViewModel(private val firebaseAuthHelper: FirebaseAuthenticationHelperInterface, private val activityLauncher: ActivityLauncherInterface) : ViewModel() {
    fun createAccount(email: String, password: String) {
        firebaseAuthHelper.createAccount(email, password, createAccountCallback)
    }

    private val createAccountCallback: (Boolean) -> Unit = { isSuccessful ->
        if (isSuccessful) {
            // start activity
            activityLauncher.startActivity(MainActivity::class.java)
        } else {
            // toast
        }
    }
}