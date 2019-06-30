package timely.com.timely.vms

import android.text.TextUtils
import android.util.Patterns
import androidx.lifecycle.ViewModel
import timely.com.timely.activities.MainActivity
import timely.com.timely.helpers.ActivityLauncher
import timely.com.timely.helpers.FirebaseAuthenticationHelper

class SignUpFragmentViewModelImpl(
    private val firebaseAuthHelper: FirebaseAuthenticationHelper,
    private val activityLauncher: ActivityLauncher
) : SignUpFragmentViewModel, ViewModel() {

    override var showSpinnerAndMakeUIUnresponsiveCallback: () -> Unit = {}

    override fun createAccountIfValid(email: String, password: String) {
        when {
            isValidEmail(email) && password.length >= 7 -> {
                createAccount(email, password)
            }
            !isValidEmail(email) && password.length < 7 -> {
                // show both errors
            }
            !isValidEmail(email) -> {
                // show email error
            }
            else -> {
                // show password error
            }
        }
    }

    private val createAccountCallback: (Boolean) -> Unit = { isSuccessful ->
        // stop the spinner + make UI responsive
        if (isSuccessful) {
            // start activity
            activityLauncher.startActivity(MainActivity::class.java)
        } else {
            // show error in sign up screen
        }
    }

    private fun isValidEmail(target: CharSequence): Boolean {
        return !TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target).matches()
    }

    private fun createAccount(email: String, password: String) {
        firebaseAuthHelper.createAccount(email, password, createAccountCallback)
        showSpinnerAndMakeUIUnresponsiveCallback.invoke()
    }
}


interface SignUpFragmentViewModel {
    fun createAccountIfValid(email: String, password: String)
    var showSpinnerAndMakeUIUnresponsiveCallback: () -> Unit
}