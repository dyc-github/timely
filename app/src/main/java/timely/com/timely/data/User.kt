package timely.com.timely.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class User(
    val email: String = "",
    val firstName: String = "",
    val lastName: String = "",
    val middleName: String = "",
    @PrimaryKey @ColumnInfo(name = "user_id") val uid: String = "",
    val school: String = "",
    val strikes: Int = 0,
    val wins: Int = 0
)