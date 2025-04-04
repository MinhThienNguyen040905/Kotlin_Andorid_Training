package com.example.autocompletetextview

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.autocompletetextview.databinding.ActivityMainBinding

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

        val provinces_list=resources.getStringArray(R.array.provinces_list)
        // Tạo adapter
        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, provinces_list)

// Gán adapter cho AutoCompleteTextView
        binding.autoProvinces.setAdapter(adapter) // Dùng phương thức setAdapter() thay vì gán trực tiếp
        binding.autoProvinces.setOnFocusChangeListener { v, hasFocus -> if(hasFocus) binding.autoProvinces.showDropDown() } 
        binding.autoProvinces.setOnItemClickListener { parent, view, position, id ->
            Toast.makeText(this,"Bạn đã chọn "+ binding.autoProvinces.text.toString(),Toast.LENGTH_LONG).show()
        }
    }
}