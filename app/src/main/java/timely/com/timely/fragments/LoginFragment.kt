package timely.com.timely.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.fragment_login.*
import timely.com.timely.R
import timely.com.timely.TimelyApplication
import timely.com.timely.vms.LoginFragmentViewModel
import javax.inject.Inject

class LoginFragment : Fragment() {

    @Inject
    lateinit var viewModel: LoginFragmentViewModel

    private lateinit var auth: FirebaseAuth

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        ((context as AppCompatActivity).application as TimelyApplication).component.inject(this)

        auth = FirebaseAuth.getInstance()

        viewModel.showSpinnerAndMakeUIUnresponsiveCallback = { showSpinner ->
            if (showSpinner) {
                progress_spinner.visibility = View.VISIBLE
                loading_back.visibility = View.VISIBLE
                email_edit_text.isEnabled = false
                password_edit_text.isEnabled = false
                log_in_button.isEnabled = false
            } else {
                progress_spinner.visibility = View.INVISIBLE
                loading_back.visibility = View.INVISIBLE
                email_edit_text.isEnabled = true
                password_edit_text.isEnabled = true
                log_in_button.isEnabled = true
            }
        }


        viewModel.loginFailCallback = {
            val snackbar = Snackbar.make(log_in_view, R.string.login_failed, Snackbar.LENGTH_SHORT)
            snackbar.show()
        }

        sign_up_redirect.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_signUpFragment)
        }



        log_in_button.setOnClickListener {
                val email = email_edit_text.text.toString()
                val password = password_edit_text.text.toString()
            if (email != "" && password != ""){
                viewModel.logInIfValid(email, password, auth)}

            else{
                viewModel.loginFailCallback()
            }
        }


    }
}