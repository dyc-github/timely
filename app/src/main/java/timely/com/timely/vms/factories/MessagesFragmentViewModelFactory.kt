package timely.com.timely.vms.factories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.google.firebase.auth.FirebaseAuth
import timely.com.timely.helpers.FirestoreService
import timely.com.timely.vms.MessagesFragmentViewModel
import javax.inject.Inject

@Suppress("UNCHECKED_CAST")
class MessagesFragmentViewModelFactory @Inject constructor(private val firestoreService: FirestoreService, private val firebaseAuth: FirebaseAuth): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MessagesFragmentViewModel(firestoreService, firebaseAuth) as T
    }
}