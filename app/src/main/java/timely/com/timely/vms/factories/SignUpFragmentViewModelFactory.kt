package timely.com.timely.vms.factories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import timely.com.timely.helpers.ActivityLauncherInterface
import timely.com.timely.helpers.FirebaseAuthenticationHelperInterface
import timely.com.timely.vms.SignUpFragmentViewModel

@Suppress("UNCHECKED_CAST")
class SignUpFragmentViewModelFactory(private val firebaseAuthHelper: FirebaseAuthenticationHelperInterface, private val activityLauncher: ActivityLauncherInterface) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return SignUpFragmentViewModel(firebaseAuthHelper = firebaseAuthHelper, activityLauncher = activityLauncher) as T
    }
}
