package timely.com.timely.vms

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import timely.com.timely.data.User
import timely.com.timely.helpers.FirestoreService
import timely.com.timely.repositories.UserRepository

class ProfileFragmentViewModelImpl(lifecycleOwner: LifecycleOwner, private val firestoreService: FirestoreService, private val firebaseAuth: FirebaseAuth, private val userRepository: UserRepository) : ProfileFragmentViewModel, ViewModel() {
    override var firstNameCallback: (firstName: String) -> Unit = {}
    override var middleNameCallback: (middleName: String) -> Unit = {}
    override var lastNameCallback: (lastName: String) -> Unit = {}
    override var schoolCallback: (school: String) -> Unit = {}
    override var strikesCallback: (strikes: String) -> Unit = {}
    override var winsCallback: (wins: String) -> Unit = {}

    private val userCallback: (callback: User?) -> Unit = { user ->
        userRepository.userLiveData.postValue(user)
    }

    private val userObserver = Observer<User> { user ->
        if (user != null) {
            firstNameCallback.invoke(user.firstName)
            middleNameCallback.invoke(user.middleName)
            lastNameCallback.invoke(user.lastName)
            schoolCallback.invoke(user.school)
            strikesCallback.invoke(user.strikes.toString())
            winsCallback.invoke(user.wins.toString())
        } else {
            firstNameCallback.invoke("ERRROOOOOORRRRR")
        }
    }

    init {
        userRepository.userLiveData.observe(lifecycleOwner, userObserver)
    }

    override fun bind() {
        userRepository.update()
        firebaseAuth.currentUser?.uid?.let { uid ->
            firestoreService.getUser(userCallback, uid)
        }
    }
}