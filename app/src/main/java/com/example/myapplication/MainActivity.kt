package com.example.myapplication

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.chat.ChatListAdapter
import com.example.myapplication.Chat.Chat
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var chatListAdapter: ChatListAdapter

    // Оригинальный список чатов
    private val originalChatList = listOf(
        Chat("Иван Иванов", "Последнее сообщение 1", R.drawable.ic_profile),
        Chat("Мария Петрова", "Последнее сообщение 2", R.drawable.ic_profile),
        Chat("Алексей Сидоров", "Последнее сообщение 3", R.drawable.ic_profile)
        // ... другие чаты
    )

    // Отображаемый список чатов
    private var displayedChatList = originalChatList.toMutableList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Инициализация RecyclerView и адаптера
        recyclerView = findViewById(R.id.recycler_view)
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_navigation)

        chatListAdapter = ChatListAdapter(displayedChatList)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = chatListAdapter
    }

    // Создаем меню с SearchView
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.search_menu, menu)

        // Получаем SearchView и настраиваем его
        val searchItem = menu?.findItem(R.id.action_search)
        val searchView = searchItem?.actionView as SearchView

        // Настраиваем действия при вводе текста
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                // Обрабатываем отправку запроса (нажатие Enter)
                filterChatList(query ?: "")
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                // Обрабатываем изменение текста в поисковом поле
                filterChatList(newText ?: "")
                return false
            }
        })

        return true
    }

    // Метод для фильтрации списка чатов
    private fun filterChatList(query: String) {
        val filteredList = if (query.isEmpty()) {
            originalChatList
        } else {
            originalChatList.filter { chat ->
                chat.chatName.contains(query, ignoreCase = true) ||
                        chat.lastMessage.contains(query, ignoreCase = true)
            }
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
