package timely.com.timely.repositories

import androidx.lifecycle.MutableLiveData
import timely.com.timely.data.User
import javax.inject.Inject

class UserRepository @Inject constructor() {
    val userLiveData = MutableLiveData<User>()

    fun update() {
        userLiveData.value?.let {
            userLiveData.postValue(it)
        }
    }
}
