package timely.com.timely.helpers

import com.google.firebase.firestore.FirebaseFirestore
import timely.com.timely.data.Message
import timely.com.timely.data.User
import timely.com.timely.repositories.UserRepository
import javax.inject.Inject

class FirestoreServiceImpl @Inject constructor(private val firebaseFirestore: FirebaseFirestore, private val userRepository: UserRepository) : FirestoreService {
    override fun getUser(callback: (User?) -> Unit, userId: String) {
        firebaseFirestore.collection("users").whereEqualTo("uid", userId)
            .get()
            .addOnSuccessListener {
                val user = it.first().toObject(User::class.java)
                callback.invoke(user)
            }.addOnFailureListener {
                callback.invoke(null)
            }.addOnCanceledListener {
                callback.invoke(null)
            }
    }

    override fun createUser(user: User, callback: (Boolean) -> Unit) {
        firebaseFirestore.collection("users").document(user.uid).set(user).addOnSuccessListener {
            callback.invoke(true)
            userRepository.addNewUserToDao(user)
        }.addOnCanceledListener {
            callback.invoke(false)
        }.addOnFailureListener {
            callback.invoke(false)
        }
    }

    override fun postMessage(message: Message, callback: (Boolean) -> Unit) {
        firebaseFirestore.collection("messages").add(message).addOnSuccessListener {
            callback.invoke(true)
        }.addOnCanceledListener {
            callback.invoke(false)
        }.addOnFailureListener {
            callback.invoke(false)
        }
    }

    override fun getPrivateMessages(callback: (ArrayList<Message>?) -> Unit, userId: String) {
        firebaseFirestore.collection("messages").whereEqualTo("userID", userId)
            .get()
            .addOnSuccessListener { messages ->
                val messagesList = arrayListOf<Message>()
                for (message in messages) {
                    messagesList.add(message.toObject(Message::class.java))
                }
                messagesList.sortByDescending { it.timeStamp }
                callback.invoke(messagesList)
            }
    }
}