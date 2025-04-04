package com.example.imageview_imagebutton

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.imageview_imagebutton.databinding.ActivityMainBinding

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
        addEvent()


    }

    private fun addEvent() {
        binding.radKotlin.setOnCheckedChangeListener { buttonView, isChecked ->
            if(isChecked) binding.imgImage.setImageResource(R.drawable.img_kotlin)
        }
        binding.radJava.setOnCheckedChangeListener { buttonView, isChecked ->
            if(isChecked) binding.imgImage.setImageResource(R.drawable.img_java)
        }
        binding.radCs.setOnCheckedChangeListener { buttonView, isChecked ->
            if(isChecked) binding.imgImage.setImageResource(R.drawable.img_cs)
        }

        binding.btnExit.setOnClickListener {
            finish()
        }
    }


}