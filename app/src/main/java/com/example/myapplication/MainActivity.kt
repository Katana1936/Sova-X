package com.example.myapplication

import Chat.Chat
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.chat.ChatListAdapter
import android.widget.EditText

class MainActivity : AppCompatActivity() {

    private lateinit var searchEditText: EditText
    private lateinit var recyclerView: RecyclerView
    private lateinit var chatListAdapter: ChatListAdapter

    // Оригинальный список чатов
    private val originalChatList = listOf(
        Chat("Иван Иванов", "Последнее сообщение 1", R.drawable.ic_profile),
        // ... другие чаты
    )

    // Отображаемый список чатов
    private var displayedChatList = originalChatList.toMutableList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Инициализация элементов интерфейса
        searchEditText = findViewById(R.id.search_edit_text)
        recyclerView = findViewById(R.id.recycler_view)
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_navigation)

        // Настройка RecyclerView и адаптера
        chatListAdapter = ChatListAdapter(displayedChatList)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = chatListAdapter

        // Настройка BottomNavigationView (без изменений)
        // ...

        // Добавление слушателя для EditText
        searchEditText.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                val query = s?.toString() ?: ""
                filterChatList(query)
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                // Не требуется
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                // Не требуется
            }
        })
    }

    // Метод для фильтрации списка чатов
    private fun filterChatList(query: String) {
        val filteredList = originalChatList.filter { chat ->
            chat.chatName.contains(query, ignoreCase = true) ||
                    chat.lastMessage.contains(query, ignoreCase = true)
        }
        updateChatList(filteredList)
    }

    // Метод для обновления адаптера с новым списком
    private fun updateChatList(newList: List<Chat>) {
        displayedChatList.clear()
        displayedChatList.addAll(newList)
        chatListAdapter.notifyDataSetChanged()
    }
}
