package com.example.myapplication

import android.os.Bundle
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationBarView
import com.google.android.material.search.SearchBar
import com.google.android.material.search.SearchView

class MainActivity : AppCompatActivity() {

    private lateinit var  searcBar: SearchBar
    private lateinit var  searcView: SearchView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        searcBar = findViewById(R.id.search_bar)

        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_navigation)


        bottomNavigationView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.item_1 -> {

                    true
                }
                R.id.item_2 -> {

                    true
                }
                R.id.item_3 -> {

                    true
                }
                R.id.item_4 -> {

                    true
                }
                else -> false
            }
        }


        bottomNavigationView.setOnItemReselectedListener { item ->
            when (item.itemId) {
                R.id.item_1 -> {

                }
                R.id.item_2 -> {

                }
                R.id.item_3 -> {

                }
                R.id.item_4 -> {

                }
            }
        }


        val badge = bottomNavigationView.getOrCreateBadge(R.id.item_3)
        badge.isVisible = true
        badge.number = 5


        val badgeDrawable = bottomNavigationView.getBadge(R.id.item_3)
        badgeDrawable?.isVisible = false
        badgeDrawable?.clearNumber()
    }
}
