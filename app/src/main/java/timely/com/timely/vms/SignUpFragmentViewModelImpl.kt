package timely.com.timely.vms

import androidx.lifecycle.ViewModel
import timely.com.timely.activities.MainActivity
import timely.com.timely.helpers.ActivityLauncher
import timely.com.timely.helpers.FirebaseAuthenticationHelper

class SignUpFragmentViewModelImpl(private val firebaseAuthHelper: FirebaseAuthenticationHelper, private val activityLauncher: ActivityLauncher) : SignUpFragmentViewModel, ViewModel() {
    override fun createAccount(email: String, password: String) {
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

interface SignUpFragmentViewModel {
    fun createAccount(email: String, password: String)
}