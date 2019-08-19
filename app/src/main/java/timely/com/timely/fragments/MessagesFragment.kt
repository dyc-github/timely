package timely.com.timely.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.Timestamp
import kotlinx.android.synthetic.main.fragment_messages.*

import timely.com.timely.R
import timely.com.timely.adapters.ChatGroupAdapter
import timely.com.timely.data.ChatGroup
import timely.com.timely.vms.MessagesFragmentViewModel
import javax.inject.Inject

class MessagesFragment : Fragment() {

    @Inject
    lateinit var viewModel: MessagesFragmentViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_messages, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val viewManager = LinearLayoutManager(view.context)
        val chatGroup1 = ChatGroup("Kevin Seo", Timestamp.now(), listOf(), "")
        val chatGroup2 = ChatGroup("Bro", Timestamp.now(), listOf(), "")
        val chatGroup3 = ChatGroup("Study Group for Stats", Timestamp.now(), listOf(), "")
        val chatGroups = listOf(chatGroup1, chatGroup2, chatGroup3)
        val chatGroupAdapter = ChatGroupAdapter(chatGroups)
        chat_groups_recycler_view.apply {
            setHasFixedSize(true)
            layoutManager = viewManager
            adapter = chatGroupAdapter
        }
    }
}
