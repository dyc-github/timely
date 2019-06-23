package timely.com.timely.vms

import android.text.TextUtils
import android.util.Patterns
import androidx.lifecycle.ViewModel
import timely.com.timely.activities.MainActivity
import timely.com.timely.enums.ValidationState
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

    private fun validate(email: String, password: String): ValidationState {
        if (isValidEmail(email) && password.length >= 7) {
            return ValidationState.VALID
        } else if (!(isValidEmail(email)) && !(password.length >= 7)) {
            return ValidationState.INVALIDBOTH
        } else if (!(isValidEmail(email))) {
            return ValidationState.INVALIDEMAIL
        } else {
            return ValidationState.INVALIDPASS
        }
    }

    fun isValidEmail(target: CharSequence): Boolean {
        return !TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target).matches()
    }
}


interface SignUpFragmentViewModel {
    fun createAccount(email: String, password: String)
}