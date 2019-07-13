package timely.com.timely.vms

import androidx.lifecycle.ViewModel
import timely.com.timely.data.User
import timely.com.timely.helpers.FirestoreService

class ProfileFragmentViewModelImpl(private val firestoreService: FirestoreService) : ProfileFragmentViewModel, ViewModel() {
    override var firstNameCallback: (firstName: String) -> Unit = {}
    override var middleNameCallback: (middleName: String) -> Unit = {}
    override var lastNameCallback: (lastName: String) -> Unit = {}
    override var schoolCallback: (school: String) -> Unit = {}
    override var strikesCallback: (strikes: String) -> Unit = {}
    override var winsCallback: (wins: String) -> Unit = {}

    private val userCallback: (callback: User?) -> Unit

    init {
        userCallback = { user ->
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
    }

    override fun bind() {
        firestoreService.getUser(userCallback)
    }
}