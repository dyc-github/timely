package timely.com.timely.vms

import android.util.Log
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import timely.com.timely.data.Message
import timely.com.timely.helpers.FirestoreService

class MessagesFragmentViewModel(private val firestoreService: FirestoreService, private val firebaseAuth: FirebaseAuth) : ViewModel() {

    var sendMessageCallback: (Boolean) -> Unit = {
        Log.d("MESSAGE_CALLBACK", it.toString())
    }

    fun sendMessage(text: String) {
        val message = Message(firstName = "Kevin", lastName = "Seo", message = text,
            userId = firebaseAuth.currentUser?.uid, groupId = null)
        firestoreService.postMessage(message, sendMessageCallback)
    }

    fun getMessages() {
        val callback: (ArrayList<Message>?) -> Unit  = { messages ->
            messages?.let {
                for (message in messages) {
                    Log.d("MESSAGE", "${message.message} by ${message.firstName} ${message.lastName} on ${message.timeStamp.toDate()}")
                }
            }
        }
        firebaseAuth.currentUser?.uid?.let { userId ->
            firestoreService.getPrivateMessages(callback, userId)
        }

    }
}