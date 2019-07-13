package timely.com.timely.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.fragment_profile.*
import timely.com.timely.R
import timely.com.timely.TimelyApplication
import timely.com.timely.vms.ProfileFragmentViewModel
import javax.inject.Inject

class ProfileFragment : Fragment() {

    @Inject
    lateinit var viewModel: ProfileFragmentViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        ((context as AppCompatActivity).application as TimelyApplication).component.inject(this)
        bind()
    }

    private fun bind() {
        viewModel.firstNameCallback = {
            first_name.text = it
        }
        viewModel.middleNameCallback = {
            middle_name.text = it
        }
        viewModel.lastNameCallback = {
            last_name.text = it
        }
        viewModel.schoolCallback = {
            school_name.text = it
        }
        viewModel.strikesCallback = {
            strikes.text = it
        }
        viewModel.bind()
    }
}
