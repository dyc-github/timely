package timely.com.timely.dagger

import dagger.Component
import timely.com.timely.fragments.LoginFragment
import timely.com.timely.fragments.MessagesFragment
import timely.com.timely.fragments.ProfileFragment
import timely.com.timely.fragments.SignUpFragment
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class])
interface AppComponent {
    fun inject(signUpFragment: SignUpFragment)
    fun inject(loginFragment: LoginFragment)
    fun inject(profileFragment: ProfileFragment)
    fun inject(messagesFragment: MessagesFragment)
}
