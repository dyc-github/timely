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
    private val activityLauncher: ActivityLauncher) : LoginFragmentViewModel, ViewModel() {

    override var showSpinnerAndMakeUIUnresponsiveCallback: (Boolean) -> Unit = {}
    override var userSignedInCallback: () -> Unit = {}
    override var loginFailCallback: () -> Unit = {}


    override fun logInIfValid(email: String, password: String, auth: FirebaseAuth) {
        showSpinnerAndMakeUIUnresponsiveCallback(true)
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                showSpinnerAndMakeUIUnresponsiveCallback(false)
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d(TAG, "signInWithEmail:success")
                    val user = auth.currentUser
                    activityLauncher.startActivity(MainActivity::class.java)                }
                else {
                    // If sign in fails, display a message to the user.
                    Log.w(TAG, "signInWithEmail:failure", task.exception)
                    loginFailCallback.invoke()

                }
            }
        }

    override fun signUpRedirect(){
        activityLauncher.startActivity(SignUpFragment::class.java)
    }


//
//    private val logInCallback: (Boolean) -> Unit = { isSuccessful ->
//        // stop the spinner + make UI responsive (do I really have to do this?)
//        showSpinnerAndMakeUIUnresponsiveCallback(false)
//        if (isSuccessful) {
//            // start activity
//            activityLauncher.startActivity(MainActivity::class.java)
//        } else {
//            loginFailCallback.invoke()
//        }
//    }
//
//    private fun isValidEmail(target: CharSequence): Boolean {
//        return !TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target).matches()
//    }
}


interface LoginFragmentViewModel {
    fun signUpRedirect()
    fun logInIfValid(email: String, password: String, auth:FirebaseAuth)
    var showSpinnerAndMakeUIUnresponsiveCallback: (Boolean) -> Unit
    var userSignedInCallback: () -> Unit
    var loginFailCallback: () -> Unit
}