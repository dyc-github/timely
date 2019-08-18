package timely.com.timely.vms

import android.content.ContentValues.TAG
import android.text.TextUtils
import android.util.Log
import android.util.Patterns
import android.widget.Toast
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import timely.com.timely.activities.MainActivity
import timely.com.timely.fragments.SignUpFragment
import timely.com.timely.helpers.ActivityLauncher
import timely.com.timely.helpers.FirebaseAuthenticationHelper

class LoginFragmentViewModelImpl(
    private val firebaseAuthHelper: FirebaseAuthenticationHelper,
    private val activityLauncher: ActivityLauncher,
    private val firebaseAuth: FirebaseAuth
) : LoginFragmentViewModel, ViewModel() {

    override var showSpinnerAndMakeUIUnresponsiveCallback: (Boolean) -> Unit = {}
    override var userSignedInCallback: () -> Unit = {}
    override var loginFailCallback: () -> Unit = {}

    override fun logInIfValid(email: String, password: String) {
        showSpinnerAndMakeUIUnresponsiveCallback(true)
        firebaseAuth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                showSpinnerAndMakeUIUnresponsiveCallback(false)
                if (task.isSuccessful) {
                    activityLauncher.startActivity(MainActivity::class.java)
                } else {
                    Log.w(TAG, "signInWithEmail:failure", task.exception)
                    loginFailCallback.invoke()

                }
            }
    }

    override fun signUpRedirect() {
        activityLauncher.startActivity(SignUpFragment::class.java)
    }
}

interface LoginFragmentViewModel {
    fun signUpRedirect()
    fun logInIfValid(email: String, password: String)
    var showSpinnerAndMakeUIUnresponsiveCallback: (Boolean) -> Unit
    var userSignedInCallback: () -> Unit
    var loginFailCallback: () -> Unit
}