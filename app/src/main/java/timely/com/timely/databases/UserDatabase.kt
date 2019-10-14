package timely.com.timely.databases

import androidx.room.Database
import androidx.room.RoomDatabase
import timely.com.timely.dao.UserDao
import timely.com.timely.data.User

@Database(entities = arrayOf(User::class), version = 1, exportSchema = false)
abstract class UserDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
}