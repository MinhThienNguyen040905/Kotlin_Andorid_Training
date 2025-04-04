package com.example.firebase_start

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.firebase_start.databinding.ActivityAddBinding
import com.example.firebase_start.databinding.ActivityMainBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class AddActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAddBinding
    private lateinit var dbRef : DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddBinding.inflate(layoutInflater)
        setContentView(binding.root)
        enableEdgeToEdge()

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        dbRef=FirebaseDatabase.getInstance().getReference("Employees")

        binding.btnAddItem.setOnClickListener {
            val Name=binding.edtName.text.toString()
            val Age=binding.edtAge.text.toString()
            val Salary=binding.edtSalary.text.toString()

            val ID=dbRef.push().key!!

            val employee=Imployee(ID,Name,Age,Salary)
            dbRef.child(ID).setValue(employee)
                .addOnCompleteListener {
                    Toast.makeText(this,"Đã thêm dữ liệu thành công",Toast.LENGTH_LONG).show()
                    binding.edtName.text.clear()
                    binding.edtAge.text.clear()
                    binding.edtSalary.text.clear()
                }
                .addOnFailureListener {err->
                    Toast.makeText(this,"Lỗi ${err.message.toString()}",Toast.LENGTH_LONG).show()

                }



        }

    }
}