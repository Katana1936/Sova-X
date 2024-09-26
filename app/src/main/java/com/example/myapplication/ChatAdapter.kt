package com.example.myapplication

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myapplication.R

data class Chat(val chatName: String, val lastMessage: String, val photoUrl: String)

class ChatListAdapter(private val chatList: List<Chat>) : RecyclerView.Adapter<ChatListAdapter.ChatViewHolder>() {

    class ChatViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val chatNameTextView: TextView = view.findViewById(R.id.chat_name_text_view)
        val lastMessageTextView: TextView = view.findViewById(R.id.last_message_text_view)
        val chatImageView: ImageView = view.findViewById(R.id.chat_image_view)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_chat_list, parent, false)
        return ChatViewHolder(view)
    }

    override fun onBindViewHolder(holder: ChatViewHolder, position: Int) {
        val chat = chatList[position]
        holder.chatNameTextView.text = chat.chatName
        holder.lastMessageTextView.text = chat.lastMessage

        Glide.with(holder.itemView.context)
            .load(chat.photoUrl)
            .into(holder.chatImageView)
    }

    override fun getItemCount(): Int = chatList.size
}
