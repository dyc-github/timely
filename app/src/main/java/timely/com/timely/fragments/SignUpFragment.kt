package timely.com.timely.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.fragment_onbaording_launch.sign_up_button
import kotlinx.android.synthetic.main.fragment_sign_up.*
import timely.com.timely.R
import timely.com.timely.helpers.ActivityLauncher
import timely.com.timely.helpers.FirebaseAuthenticationHelper
import timely.com.timely.vms.SignUpFragmentViewModel
import timely.com.timely.vms.factories.SignUpFragmentViewModelFactory


class SignUpFragment : Fragment() {

    private lateinit var viewModel: SignUpFragmentViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_sign_up, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = getViewModel()
        sign_up_button.setOnClickListener {
            viewModel.createAccount(email_edit_text.text.toString(), password_edit_text.text.toString())
        }
    }

    private fun getViewModel(): SignUpFragmentViewModel {
        val viewModelFactory = SignUpFragmentViewModelFactory(FirebaseAuthenticationHelper(context as AppCompatActivity,
            FirebaseAuth.getInstance()), 
            ActivityLauncher(context as AppCompatActivity))
        return ViewModelProviders.of(this, viewModelFactory).get(SignUpFragmentViewModel::class.java)
    }
}