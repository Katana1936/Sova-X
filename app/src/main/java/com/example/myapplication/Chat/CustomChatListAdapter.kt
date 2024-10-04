package com.example.myapplication

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.Chat.Chat

class CustomChatListAdapter(
    private val chatList: List<Chat>,
    private val onChatClick: (Chat) -> Unit
) : RecyclerView.Adapter<CustomChatListAdapter.ChatViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_chat_list, parent, false)
        return ChatViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ChatViewHolder, position: Int) {
        val chat = chatList[position]
        holder.bind(chat)
        holder.itemView.setOnClickListener {
            onChatClick(chat)
        }
    }

    override fun getItemCount() = chatList.size

    class ChatViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(chat: Chat) {
            // Привязка данных чата
        }
    }
}
