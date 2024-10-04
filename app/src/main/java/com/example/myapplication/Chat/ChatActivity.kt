package com.example.myapplication

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.R

class ChatActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)

        // Получаем данные, переданные из MainActivity
        val chatName = intent.getStringExtra("chat_name")
        val chatId = intent.getIntExtra("chat_id", -1)

    }
}
