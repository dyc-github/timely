package timely.com.timely.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_onbaording_launch.sign_up_button
import kotlinx.android.synthetic.main.fragment_sign_up.*
import timely.com.timely.R
import timely.com.timely.TimelyApplication
import timely.com.timely.enums.InvalidInput
import timely.com.timely.vms.SignUpFragmentViewModel
import javax.inject.Inject

class SignUpFragment : Fragment() {

    @Inject
    lateinit var viewModel: SignUpFragmentViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_sign_up, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        ((context as AppCompatActivity).application as TimelyApplication).component.inject(this)

        viewModel.showSpinnerAndMakeUIUnresponsiveCallback = { showSpinner ->
            if(showSpinner) {
                progress_spinner.visibility = View.VISIBLE
                loading_back.visibility = View.VISIBLE
                email_edit_text.isEnabled = false
                password_edit_text.isEnabled = false
                sign_up_button.isEnabled = false
            }
            else{
                progress_spinner.visibility = View.INVISIBLE
                loading_back.visibility = View.INVISIBLE
                email_edit_text.isEnabled = true
                password_edit_text.isEnabled = true
                sign_up_button.isEnabled = true
            }
        }

        viewModel.inputError = { invalidInput ->
            //Based off input give UI error
            if(invalidInput == InvalidInput.INVALIDEMAIL || invalidInput == InvalidInput.INVALIDBOTH){
                email_edit_text.setError("Ya messed up the email ya dimwit")
            }
            if(invalidInput == InvalidInput.INVALIDPASSWORD || invalidInput == InvalidInput.INVALIDBOTH){
                password_edit_text.setError("The paasword's wrong ya retard")
            }
        }

        viewModel.toastOutputCallbackFail = {
            val snackbar = Snackbar.make(signupview, "This is Simple Snackbar", Snackbar.LENGTH_SHORT)
            snackbar.show()
        }


        sign_up_button.setOnClickListener {
            val email = email_edit_text.text.toString()
            val password = password_edit_text.text.toString()
            viewModel.createAccountIfValid(email, password)
        }
    }
}