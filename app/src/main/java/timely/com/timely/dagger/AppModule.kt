package timely.com.timely.dagger

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import dagger.Module
import dagger.Provides
import dagger.Reusable
import timely.com.timely.helpers.ActivityLauncherImpl
import timely.com.timely.helpers.ActivityLauncher
import timely.com.timely.helpers.FirebaseAuthenticationHelperImpl
import timely.com.timely.helpers.FirebaseAuthenticationHelper
import timely.com.timely.vms.LoginFragmentViewModel
import timely.com.timely.vms.LoginFragmentViewModelImpl
import timely.com.timely.vms.SignUpFragmentViewModel
import timely.com.timely.vms.SignUpFragmentViewModelImpl
import timely.com.timely.vms.factories.LoginFragmentViewModelFactory
import timely.com.timely.vms.factories.SignUpFragmentViewModelFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
class AppModule(private val activity: AppCompatActivity) {

    @Provides
    fun provideActivity(): AppCompatActivity = activity

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
    fun provideFirebaseAuthentication(): FirebaseAuth = FirebaseAuth.getInstance()

    @Provides
    fun provideSignUpFragmentViewModel(@Named("SignUpFragmentViewModelFactory") factory: ViewModelProvider.Factory): SignUpFragmentViewModel {
        return ViewModelProviders.of(activity, factory).get(SignUpFragmentViewModelImpl::class.java)
    }

    @Provides
    fun provideLoginFragmentViewModel(@Named("LoginFragmentViewModelFactory") factory: ViewModelProvider.Factory): LoginFragmentViewModel {
        return ViewModelProviders.of(activity, factory).get(LoginFragmentViewModelImpl::class.java)
    }
}