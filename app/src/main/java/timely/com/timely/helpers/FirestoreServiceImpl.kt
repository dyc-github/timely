package timely.com.timely.helpers

import com.google.firebase.firestore.FirebaseFirestore
import timely.com.timely.data.User
import javax.inject.Inject

class FirestoreServiceImpl @Inject constructor(private val firebaseFirestore: FirebaseFirestore) : FirestoreService {
    override fun getUser(callback: (User?) -> Unit) {
        firebaseFirestore.collection("users").document("sample_user")
            .get()
            .addOnSuccessListener {
                callback.invoke(it.toObject(User::class.java))
            }.addOnFailureListener {
                callback.invoke(null)
            }.addOnCanceledListener {
                callback.invoke(null)
            }
    }
}