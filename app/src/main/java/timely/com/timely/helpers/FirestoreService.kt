package timely.com.timely.helpers

import timely.com.timely.data.Message
import timely.com.timely.data.User

interface FirestoreService {
    fun getUser(callback: (User?) -> Unit, userId: String)
    fun createUser(user: User, callback: (Boolean) -> Unit)
    fun postMessage(message: Message, callback: (Boolean) -> Unit)
    fun getPrivateMessages(callback: (ArrayList<Message>?) -> Unit, userId: String)
}
