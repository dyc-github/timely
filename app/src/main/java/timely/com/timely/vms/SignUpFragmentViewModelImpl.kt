package timely.com.timely.vms

import android.text.TextUtils
import android.util.Patterns
import androidx.lifecycle.ViewModel
import timely.com.timely.activities.MainActivity
import timely.com.timely.helpers.ActivityLauncher
import timely.com.timely.helpers.FirebaseAuthenticationHelper


class SignUpFragmentViewModelImpl(
    private val firebaseAuthHelper: FirebaseAuthenticationHelper,
    private val activityLauncher: ActivityLauncher) : SignUpFragmentViewModel, ViewModel() {

    override var invalidEmailCallback: () -> Unit = {}
    override var invalidPasswordCallback: () -> Unit = {}
    override var showSpinnerAndMakeUIUnresponsiveCallback: (Boolean) -> Unit = {}
    override var createAccountFailCallback: () -> Unit = {}


    override fun createAccountIfValid(email: String, password: String) {
        when {
            isValidEmail(email) && password.length >= 7 -> {
                createAccount(email, password)
            }
            !isValidEmail(email) && password.length < 7 -> {
                invalidEmailCallback.invoke()
                invalidPasswordCallback.invoke()
            }
            !isValidEmail(email) -> {
                invalidEmailCallback.invoke()
            }
            else -> {
                invalidPasswordCallback.invoke()
            }
        }
    }

    private val createAccountCallback: (Boolean) -> Unit = { isSuccessful ->
        // stop the spinner + make UI responsive (do I really have to do this?)
        showSpinnerAndMakeUIUnresponsiveCallback(false)
        if (isSuccessful) {
            // start activity
            activityLauncher.startActivity(MainActivity::class.java)
        } else {
            createAccountFailCallback.invoke()
            }
    }

    private fun isValidEmail(target: CharSequence): Boolean {
        return !TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target).matches()
    }

    private fun createAccount(email: String, password: String) {
        firebaseAuthHelper.createAccount(email, password, createAccountCallback)
        showSpinnerAndMakeUIUnresponsiveCallback(true)
    }
}


interface SignUpFragmentViewModel {
    fun createAccountIfValid(email: String, password: String)
    var showSpinnerAndMakeUIUnresponsiveCallback: (Boolean) -> Unit
    var invalidPasswordCallback: () -> Unit
    var invalidEmailCallback: () -> Unit
    var createAccountFailCallback:() -> Unit
}