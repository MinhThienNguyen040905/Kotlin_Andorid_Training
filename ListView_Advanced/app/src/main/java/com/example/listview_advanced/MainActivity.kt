package com.example.listview_advanced

import android.os.Bundle
import android.widget.ListView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    lateinit var customAdapter: CustomAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        var list = mutableListOf<ProgramLanguage>()
        list.add(ProgramLanguage(R.drawable.img_cs,"C#","2000"))
        list.add(ProgramLanguage(R.drawable.img_cpp,"C++","1985"))
        list.add(ProgramLanguage(R.drawable.img_js,"JavaScript","1995"))
        list.add(ProgramLanguage(R.drawable.img_java,"Java","1995"))
        list.add(ProgramLanguage(R.drawable.img_python,"Python","1991"))
        list.add(ProgramLanguage(R.drawable.img_kotlin,"Kotlin","2011"))
        list.add(ProgramLanguage(R.drawable.img_ruby,"Ruby","1995"))

        val listView=findViewById<ListView>(R.id.lv_languagelist)
     //   customAdapter=CustomAdapter(this,list)
        listView.adapter=CustomAdapter(this,list)

    }
}