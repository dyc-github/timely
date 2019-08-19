package timely.com.timely.data

data class User(
    val email: String = "",
    val firstName: String = "",
    val lastName: String = "",
    val middleName: String = "",
    val uid: String = "",
    val school: String = "",
    val strikes: Int = 0,
    val wins: Int = 0,
    val messages: Map<String, Message> = mapOf()
)