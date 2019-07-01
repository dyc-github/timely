package timely.com.timely.vms

import android.text.TextUtils
import android.util.Patterns
import androidx.lifecycle.ViewModel
import timely.com.timely.activities.MainActivity
import timely.com.timely.enums.InvalidInput
import timely.com.timely.helpers.ActivityLauncher
import timely.com.timely.helpers.FirebaseAuthenticationHelper


class SignUpFragmentViewModelImpl(
    private val firebaseAuthHelper: FirebaseAuthenticationHelper,
    private val activityLauncher: ActivityLauncher) : SignUpFragmentViewModel, ViewModel() {

    override var inputError: (InvalidInput) -> Unit = {}
    override var showSpinnerAndMakeUIUnresponsiveCallback: (Boolean) -> Unit = {}
    override var toastOutputCallbackFail: () -> Unit = {}


    override fun createAccountIfValid(email: String, password: String) {
        when {
            isValidEmail(email) && password.length >= 7 -> {
                createAccount(email, password)
            }
            !isValidEmail(email) && password.length < 7 -> {
                inputError(InvalidInput.INVALIDBOTH)
            }
            !isValidEmail(email) -> {
                inputError(InvalidInput.INVALIDEMAIL)
            }
            else -> {
                inputError(InvalidInput.INVALIDPASSWORD)
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
            toastOutputCallbackFail.invoke()
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
    var inputError: (InvalidInput) -> Unit
    var toastOutputCallbackFail:() -> Unit
}