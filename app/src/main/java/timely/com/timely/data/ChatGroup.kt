package timely.com.timely.data

import com.google.firebase.Timestamp

data class ChatGroup(val groupName: String, val lastMessageTimeStamp: Timestamp, val messages: List<Message>, val id: String)