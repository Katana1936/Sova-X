package com.example.myapplication

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.search.SearchBar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private lateinit var searchBar: SearchBar
    private lateinit var recyclerView: RecyclerView
    private lateinit var chatListAdapter: ChatListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        searchBar = findViewById(R.id.search_bar)
        recyclerView = findViewById(R.id.recycler_view)

        val chatList = listOf(
            Chat("Иван Иванов", "Последнее сообщение 1", R.drawable.ic_profile),
            Chat("Анна Петрова", "Последнее сообщение 2", R.drawable.ic_profile),
            Chat("Алексей Смирнов", "Последнее сообщение 3", R.drawable.ic_profile)
        )

        chatListAdapter = ChatListAdapter(chatList)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = chatListAdapter

        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_navigation)

        bottomNavigationView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.item_1 -> true
                R.id.item_2 -> true
                R.id.item_3 -> true
                R.id.item_4 -> true
                else -> false
            }
        }

        bottomNavigationView.setOnItemReselectedListener { item ->
            when (item.itemId) {
                R.id.item_1 -> {}
                R.id.item_2 -> {}
                R.id.item_3 -> {}
                R.id.item_4 -> {}
            }
        }

        val badge = bottomNavigationView.getOrCreateBadge(R.id.item_3)
        badge.isVisible = true
        badge.number = 5


    }
}
