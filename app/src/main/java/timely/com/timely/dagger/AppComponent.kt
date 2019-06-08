package timely.com.timely.dagger

import com.google.firebase.database.FirebaseDatabase
import dagger.Component

@Component(modules = [AppModule::class])
interface AppComponent {
    val firebaseDatabase: FirebaseDatabase
}