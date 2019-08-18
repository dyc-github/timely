package timely.com.timely.vms.factories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import timely.com.timely.helpers.FirestoreService
import timely.com.timely.vms.ProfileFragmentViewModelImpl
import javax.inject.Inject

@Suppress("UNCHECKED_CAST")
class ProfileFragmentViewModelFactory @Inject constructor(private val firestoreService: FirestoreService) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ProfileFragmentViewModelImpl(firestoreService) as T
    }
}