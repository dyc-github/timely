package timely.com.timely.vms.factories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import timely.com.timely.helpers.ActivityLauncher
import timely.com.timely.helpers.FirebaseAuthenticationHelper
import timely.com.timely.vms.LoginFragmentViewModelImpl
import timely.com.timely.vms.SignUpFragmentViewModelImpl
import javax.inject.Inject
import javax.inject.Named

@Suppress("UNCHECKED_CAST")
class LoginFragmentViewModelFactory @Inject constructor(private val firebaseAuthHelper: FirebaseAuthenticationHelper, private val activityLauncher: ActivityLauncher) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return LoginFragmentViewModelImpl(firebaseAuthHelper = firebaseAuthHelper, activityLauncher = activityLauncher) as T
    }
}
