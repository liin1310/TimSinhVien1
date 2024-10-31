package com.example.timsinhvien

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var editTextNumber: EditText
    private lateinit var radioGroup: RadioGroup
    private lateinit var buttonShow: Button
    private lateinit var listView: ListView
    private lateinit var textViewError: TextView
    private val students = listOf(
        Student("Nguyen Van A", "SV001"),
        Student("Tran Thi B", "SV002"),
        Student("Le Van C", "SV003"),
        Student("Pham Thi D", "SV004"),
        Student("Nguyen Van E", "SV005"),
        Student("Tran Thi F", "SV006")
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        editTextNumber = findViewById(R.id.editTextNumber)
        radioGroup = findViewById(R.id.radioGroup)
        buttonShow = findViewById(R.id.buttonShow)
        listView = findViewById(R.id.listView)
        textViewError = findViewById(R.id.textViewError)

        buttonShow.setOnClickListener {
            performSearch()
        }
    }

    private fun performSearch() {
        textViewError.visibility = TextView.GONE
        val keyword = editTextNumber.text.toString().trim()
        if (keyword.length <= 2) {
            textViewError.text = "Vui lòng nhập từ khóa có độ dài lớn hơn 2 ký tự"
            textViewError.visibility = TextView.VISIBLE
            return
        }

        val selectedRadio = radioGroup.checkedRadioButtonId
        val filteredList = when (selectedRadio) {
            R.id.msv -> students.filter { it.studentId.contains(keyword, ignoreCase = true) }
            R.id.hoten -> students.filter { it.name.contains(keyword, ignoreCase = true) }
            else -> emptyList()
        }

        if (filteredList.isEmpty()) {
            textViewError.text = "Không tìm thấy sinh viên phù hợp"
            textViewError.visibility = TextView.VISIBLE
        } else {
            val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_2, android.R.id.text1, filteredList.map { "${it.name} - ${it.studentId}" })
            listView.adapter = adapter
        }
    }
}
