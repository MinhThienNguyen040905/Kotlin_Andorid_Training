package com.example.boardcast_receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.boardcast_receiver.databinding.ActivityMainBinding
import android.content.Context.RECEIVER_NOT_EXPORTED

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        enableEdgeToEdge()

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        // Đăng ký receiver để nhận update UI
        val filter = IntentFilter("boardcast_receiver")
        registerReceiver(updateUIReceiver, filter, RECEIVER_NOT_EXPORTED)

    }
    private val updateUIReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            val mode = intent?.getBooleanExtra("airplane_mode", false) ?: return
            binding.txtView.text = if (mode) "Chế độ máy bay đang bật" else "Đã tắt chế độ máy bay"
        }
    }
    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(updateUIReceiver)
    }
}