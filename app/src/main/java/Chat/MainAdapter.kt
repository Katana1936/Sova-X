package com.example.myapplication.chat

import Chat.Chat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R

class ChatListAdapter(private val chatList: List<Chat>) : RecyclerView.Adapter<ChatListAdapter.ChatViewHolder>() {


    fun updateList(newList: List<Chat>) {
        chatList = newList
        notifyDataSetChanged()
    }

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
        holder.chatImageView.setImageResource(chat.photoResId)
    }

    override fun getItemCount(): Int = chatList.size
}
