package timely.com.timely.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_onbaording_launch.sign_up_button
import kotlinx.android.synthetic.main.fragment_sign_up.*
import timely.com.timely.R
import timely.com.timely.TimelyApplication
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

        viewModel.showSpinnerAndMakeUIUnresponsiveCallback = {
            // show SPINNER
        }


        sign_up_button.setOnClickListener {
            val email = email_edit_text.text.toString()
            val password = password_edit_text.text.toString()
            viewModel.createAccountIfValid(email, password)
        }
    }
}