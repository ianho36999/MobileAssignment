package com.example.mobileassignment

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btn: Button = findViewById(R.id.loginBtn)
        btn.setOnClickListener{
            goLoginPage()
        }
    }

    private fun goLoginPage() {
        val intent = Intent(this@MainActivity, LoginPage::class.java)
        startActivity(intent)
    }
}


