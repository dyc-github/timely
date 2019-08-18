package timely.com.timely.helpers

import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import timely.com.timely.data.User
import javax.inject.Inject

class FirebaseAuthenticationHelperImpl @Inject constructor(private val activity: AppCompatActivity, private val firebaseAuth: FirebaseAuth, private val firestoreService: FirestoreService) : FirebaseAuthenticationHelper {
    override fun createAccount(email: String, password: String, callback: (Boolean) -> Unit) {
            firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(activity) { task ->
                    if (task.isSuccessful) {
                        firebaseAuth.currentUser?.uid?.let { uid ->
                            val user = User(email = email, firstName = "Jo", middleName = "Jo", lastName = "Reference", uid = uid)
                            firestoreService.createUser(user, userCreationCallback)
                        }
                    }
                    callback.invoke(task.isSuccessful)
                }
    }

    private val userCreationCallback: (Boolean) -> Unit = { isSuccessful ->
        if (!isSuccessful) {
            firebaseAuth.currentUser?.delete()
        }
    }
}

interface FirebaseAuthenticationHelper {
    fun createAccount(email: String, password: String, callback: (Boolean) -> Unit)
}