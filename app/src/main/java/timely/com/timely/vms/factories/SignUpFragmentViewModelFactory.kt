package timely.com.timely.vms.factories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import timely.com.timely.helpers.ActivityLauncher
import timely.com.timely.helpers.FirebaseAuthenticationHelper
import timely.com.timely.vms.SignUpFragmentViewModelImpl
import javax.inject.Inject

@Suppress("UNCHECKED_CAST")
class SignUpFragmentViewModelFactory @Inject constructor(private val firebaseAuthHelper: FirebaseAuthenticationHelper, private val activityLauncher: ActivityLauncher) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return SignUpFragmentViewModelImpl(firebaseAuthHelper = firebaseAuthHelper, activityLauncher = activityLauncher) as T
    }
}
