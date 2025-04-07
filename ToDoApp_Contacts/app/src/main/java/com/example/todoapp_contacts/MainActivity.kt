package com.example.todoapp_contacts

import android.Manifest
import Contact
import ContactAdapter
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.provider.ContactsContract
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.todoapp_contacts.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val contactList = mutableListOf<Contact>()
    private lateinit var adapter: ContactAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Khởi tạo Adapter
        adapter = ContactAdapter(contactList)
        binding.recyclerViewContacts.adapter = adapter
        binding.recyclerViewContacts.layoutManager = LinearLayoutManager(this)

        // Xử lý chọn liên hệ
        binding.btnPickContact.setOnClickListener {
            checkContactPermission()
        }
    }

    private fun checkContactPermission() {
        if (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.READ_CONTACTS // <-- SẼ KHÔNG CÒN LỖI
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.READ_CONTACTS), // <-- SẼ KHÔNG CÒN LỖI
                REQUEST_CONTACT_PERMISSION
            )
        } else {
            pickContact()
        }
    }

    private fun pickContact() {
        val intent = Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI)
        startActivityForResult(intent, REQUEST_PICK_CONTACT)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_PICK_CONTACT && resultCode == RESULT_OK) {
            data?.data?.let { contactUri ->
                val contact = getContactDetails(contactUri)
                contact?.let {
                    contactList.add(it)
                    adapter.notifyDataSetChanged()
                }
            }
        }
    }

    private fun getContactDetails(contactUri: Uri): Contact? {
        val contentResolver = contentResolver
        var contact: Contact? = null

        // Truy vấn thông tin liên hệ
        val cursor = contentResolver.query(
            contactUri,
            null,
            null,
            null,
            null
        )

        cursor?.use {
            if (it.moveToFirst()) {
                val nameIndex = it.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME)
                val name = it.getString(nameIndex)

                val idIndex = it.getColumnIndex(ContactsContract.Contacts._ID)
                val id = it.getString(idIndex)

                // Lấy số điện thoại
                val phoneCursor = contentResolver.query(
                    ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                    null,
                    ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = ?",
                    arrayOf(id),
                    null
                )

                phoneCursor?.use { pc ->
                    if (pc.moveToFirst()) {
                        val phoneNumberIndex = pc.getColumnIndex(
                            ContactsContract.CommonDataKinds.Phone.NUMBER
                        )
                        val phone = pc.getString(phoneNumberIndex)
                        contact = Contact(name, phone)
                    }
                }
            }
        }
        return contact
    }

    companion object {
        private const val REQUEST_CONTACT_PERMISSION = 100
        private const val REQUEST_PICK_CONTACT = 101
    }
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == REQUEST_CONTACT_PERMISSION) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                pickContact()
            } else {
                Toast.makeText(this, "Cần cấp quyền để truy cập danh bạ", Toast.LENGTH_SHORT).show()
            }
        }
    }
}