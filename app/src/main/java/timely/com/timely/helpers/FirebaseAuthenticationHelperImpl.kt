package timely.com.timely.helpers

import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import javax.inject.Inject

class FirebaseAuthenticationHelperImpl @Inject constructor(private val activity: AppCompatActivity, private val firebaseAuth: FirebaseAuth) : FirebaseAuthenticationHelper {
    override fun createAccount(email: String, password: String, callback: (Boolean) -> Unit) {
        firebaseAuth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(activity) { task ->
                callback.invoke(task.isSuccessful)
            }
    }
}

interface FirebaseAuthenticationHelper {
    fun createAccount(email: String, password: String, callback: (Boolean) -> Unit)
}