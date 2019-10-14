package timely.com.timely.repositories

import androidx.lifecycle.MutableLiveData
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import timely.com.timely.dao.UserDao
import timely.com.timely.data.User
import javax.inject.Inject

class UserRepository @Inject constructor(private val userDao: UserDao, private val firebaseAuth: FirebaseAuth) {
    val userLiveData = MutableLiveData<User>()

    private var currentJob: Job? = null

    fun refresh() {
        if (currentJob?.isActive != true) {
            currentJob = CoroutineScope(Dispatchers.IO).launch {
                val user = userDao.getUser(firebaseAuth.currentUser!!.uid)
                CoroutineScope(Dispatchers.Main).launch {
                    userLiveData.postValue(user)
                }
            }
        }
    }

    fun addNewUserToDao(user: User) {
        if (currentJob?.isActive != true) {
            currentJob = CoroutineScope(Dispatchers.IO).launch {
                userDao.insert(user)
                CoroutineScope(Dispatchers.Main).launch {
                    userLiveData.postValue(user)
                }
            }
        }
    }
}
