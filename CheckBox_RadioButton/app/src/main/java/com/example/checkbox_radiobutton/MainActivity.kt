package com.example.checkbox_radiobutton

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.checkbox_radiobutton.databinding.ActivityMainBinding

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
        binding.btnChon.setOnClickListener {
            hasChecked()
        }
    }

    private fun hasChecked() {
        var S:String=""
        if(binding.ckbNgu.isChecked)
            S+=binding.ckbNgu.text.toString()+"\n"
        if(binding.ckbAnUong.isChecked)
            S+=binding.ckbAnUong.text.toString()+"\n"
        if(binding.ckbGiatDo.isChecked)
            S+=binding.ckbGiatDo.text.toString()+"\n"
        if(binding.ckbHocBai.isChecked)
            S+=binding.ckbHocBai.text.toString()+"\n"
        if(binding.ckbNgheNhac.isChecked)
            S+=binding.ckbNgheNhac.text.toString()+"\n"
       binding.txtKetQua.setText(S)
    }


}