package timely.com.timely.helpers

import timely.com.timely.data.User

interface FirestoreService {
    fun getUser(callback: (User?) -> Unit)
    fun createUser(user: User, callback: (Boolean) -> Unit)
}
