package timely.com.timely.helpers

import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth

class FirebaseAuthenticationHelper(private val activity: AppCompatActivity, private val firebaseAuth: FirebaseAuth) : FirebaseAuthenticationHelperInterface {
    override fun createAccount(email: String, password: String, callback: (Boolean) -> Unit) {
        firebaseAuth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(activity) { task ->
                callback.invoke(task.isSuccessful)
            }
    }
}

interface FirebaseAuthenticationHelperInterface {
    fun createAccount(email: String, password: String, callback: (Boolean) -> Unit)
}