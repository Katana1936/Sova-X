package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.Chat.Chat
import com.google.android.material.bottomnavigation.BottomNavigationView


class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var chatListAdapter: CustomChatListAdapter

    private val originalChatList = listOf(
        Chat("Иван Иванов", "Последнее сообщение 1", R.drawable.ic_profile),
        Chat("Мария Петрова", "Последнее сообщение 2", R.drawable.ic_profile),
        Chat("Алексей Сидоров", "Последнее сообщение 3", R.drawable.ic_profile)
        // ... другие чаты
    )

    private var displayedChatList = originalChatList.toMutableList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recycler_view)
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_navigation)


        chatListAdapter = CustomChatListAdapter(displayedChatList) { chat -> openChat(chat) }
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = chatListAdapter
    }

    // Метод для открытия чата
    private fun openChat(chat: Any) {
        val intent = Intent(this, ChatActivity::class.java).apply {
            //putExtra("chat_name", chat.chatName) СТАСИК ТУТ ДОПИШИ
        }
        startActivity(intent)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.search_menu, menu)

        val searchItem = menu?.findItem(R.id.action_search)
        val searchView = searchItem?.actionView as SearchView

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                filterChatList(query ?: "")
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                filterChatList(newText ?: "")
                return false
            }
        })

        return true
    }

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

    private fun updateChatList(newList: List<Chat>) {
        displayedChatList.clear()
        displayedChatList.addAll(newList)
        chatListAdapter.notifyDataSetChanged()
    }
}
