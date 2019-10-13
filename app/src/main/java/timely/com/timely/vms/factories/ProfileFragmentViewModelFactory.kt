package timely.com.timely.vms.factories

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.google.firebase.auth.FirebaseAuth
import timely.com.timely.helpers.FirestoreService
import timely.com.timely.repositories.UserRepository
import timely.com.timely.vms.ProfileFragmentViewModelImpl
import javax.inject.Inject

@Suppress("UNCHECKED_CAST")
class ProfileFragmentViewModelFactory @Inject constructor(private val activity: AppCompatActivity, private val firestoreService: FirestoreService, private val firebaseAuth: FirebaseAuth, private val userRepository: UserRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ProfileFragmentViewModelImpl(activity, firestoreService, firebaseAuth, userRepository) as T
    }
}