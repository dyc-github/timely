package timely.com.timely.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.chat_group_cell.view.*
import timely.com.timely.R
import timely.com.timely.data.ChatGroup
import timely.com.timely.views.ChatGroupCellView

class ChatGroupAdapter(private val chatGroups: List<ChatGroup>) : RecyclerView.Adapter<ChatGroupCellViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatGroupCellViewHolder {
        val cell = LayoutInflater.from(parent.context).inflate(R.layout.chat_group_cell, parent, false) as ChatGroupCellView
        return ChatGroupCellViewHolder(cell)
    }

    override fun getItemCount(): Int {
        return chatGroups.size
    }

    override fun onBindViewHolder(holder: ChatGroupCellViewHolder, position: Int) {
        holder.chatGroupCellView.group_name.text = chatGroups[position].groupName
        holder.chatGroupCellView.last_message_at_text.text = "Last message at ${chatGroups[position].lastMessageTimeStamp.toDate()}"
    }
}

class ChatGroupCellViewHolder(val chatGroupCellView: ChatGroupCellView) : RecyclerView.ViewHolder(chatGroupCellView) {

}