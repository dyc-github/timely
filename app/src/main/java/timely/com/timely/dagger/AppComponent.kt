package timely.com.timely.dagger

import dagger.Component
import timely.com.timely.fragments.SignUpFragment
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class])
interface AppComponent {
    fun inject(signUpFragment: SignUpFragment)
}