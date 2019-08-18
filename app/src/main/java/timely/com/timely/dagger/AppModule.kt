package timely.com.timely.dagger

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreSettings
import dagger.Module
import dagger.Provides
import dagger.Reusable
import timely.com.timely.helpers.*
import timely.com.timely.vms.*
import timely.com.timely.vms.factories.LoginFragmentViewModelFactory
import timely.com.timely.vms.factories.ProfileFragmentViewModelFactory
import timely.com.timely.vms.factories.SignUpFragmentViewModelFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
class AppModule(private val activity: AppCompatActivity) {

    @Provides
    fun provideActivity(): AppCompatActivity = activity

    @Provides
    fun provideContext(): Context = activity

    @Provides
    @Reusable
    @Named("SignUpFragmentViewModelFactory")
    fun provideSignUpFragmentViewModelFactory(factory: SignUpFragmentViewModelFactory): ViewModelProvider.Factory = factory

    @Provides
    @Reusable
    @Named("LoginFragmentViewModelFactory")
    fun provideLoginFragmentViewmodelFactory(factory: LoginFragmentViewModelFactory): ViewModelProvider.Factory = factory

    @Provides
    @Reusable
    fun provideActivityLauncher(activityLauncher: ActivityLauncherImpl): ActivityLauncher = activityLauncher

    @Provides
    @Reusable
    fun provideFirebaseAuthenticationHelper(firebaseAuthenticationHelper: FirebaseAuthenticationHelperImpl): FirebaseAuthenticationHelper = firebaseAuthenticationHelper

    @Provides
    @Singleton
    fun provideFirebaseDatabase(): FirebaseDatabase = FirebaseDatabase.getInstance()

    @Provides
    @Singleton
    fun provideFirebaseFirestore(): FirebaseFirestore {
        val firebaseFirestore = FirebaseFirestore.getInstance()
        val settings = FirebaseFirestoreSettings.Builder()
            .setPersistenceEnabled(true)
            .setCacheSizeBytes(FirebaseFirestoreSettings.CACHE_SIZE_UNLIMITED)
            .build()
        firebaseFirestore.firestoreSettings = settings
        return firebaseFirestore
    }

    @Provides
    @Singleton
    fun provideFirebaseAuthentication(): FirebaseAuth = FirebaseAuth.getInstance()

    @Provides
    fun provideSignUpFragmentViewModel(@Named("SignUpFragmentViewModelFactory") factory: ViewModelProvider.Factory): SignUpFragmentViewModel {
        return ViewModelProviders.of(activity, factory).get(SignUpFragmentViewModelImpl::class.java)
    }

    @Reusable
    fun provideStringProvider(stringProvider: StringProviderImpl): StringProvider = stringProvider

    @Provides
    @Reusable
    fun provideFirestoreService(firestoreService: FirestoreServiceImpl): FirestoreService = firestoreService

    @Provides
    fun provideLoginFragmentViewModel(@Named("LoginFragmentViewModelFactory") factory: ViewModelProvider.Factory): LoginFragmentViewModel {
        return ViewModelProviders.of(activity, factory).get(LoginFragmentViewModelImpl::class.java)
    }

    @Provides
    fun provideProfileFragmentViewModel(factory: ProfileFragmentViewModelFactory): ProfileFragmentViewModel {
        return ViewModelProviders.of(activity, factory).get(ProfileFragmentViewModelImpl::class.java)
    }
}