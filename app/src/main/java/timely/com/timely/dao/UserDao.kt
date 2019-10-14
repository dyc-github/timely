package timely.com.timely.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import timely.com.timely.data.User

@Dao
interface UserDao {
    @Insert
    suspend fun insert(user: User)

    @Query("SELECT * FROM users WHERE user_id LIKE :uid")
    suspend fun getUser(uid: String): User
}