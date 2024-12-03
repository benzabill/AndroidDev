package com.example.neurable.ui

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.example.neurable.R

class DetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

//        val toolbar = findViewById<Toolbar>(R.id.toolbar)
//        val toolbarTitle = toolbar.findViewById<TextView>(R.id.toolbar_title)
//        val backButton = toolbar.findViewById<ImageView>(R.id.back_button)
//        setSupportActionBar(toolbar)

        val user = intent.getStringExtra("user_title")
        val body = intent.getStringExtra("user_body")

//        toolbarTitle.text = user
        val detailTextView = findViewById<TextView>(R.id.detail_text_view)
        // I would usually do a more advanced UI or use R.strings.xml here but requirements are low here
        detailTextView.text = "Body: $body"

//        backButton.setOnClickListener {
//            onBackPressed()
//        }
    }
}
