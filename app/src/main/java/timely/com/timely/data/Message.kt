package timely.com.timely.data

import com.google.firebase.Timestamp

data class Message(
    val firstName: String = "",
    val lastName: String = "",
    val message: String = "",
    val userId: String?,
    val groupId: String?,
    val timeStamp: Timestamp = Timestamp.now()
)