package com.example.hcare

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView

class healthArticlesDetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_health_articles_details)
        val backbutton=findViewById<Button>(R.id.backhealthdetails)
        val img=findViewById<ImageView>(R.id.imageviewhealthdetails)
        val head=findViewById<TextView>(R.id.subheadinghealthdetails)
        val intent:Intent=getIntent()
        head.setText(intent.getStringExtra("text1"))
        val bundle: Bundle? = intent.extras
        if (bundle != null) {
            val resid: Int = bundle.getInt("text2")
            img.setImageResource(resid)
        }
        backbutton.setOnClickListener {
            val intent = Intent(this@healthArticlesDetailsActivity, healthArticlesActivity::class.java)
            startActivity(intent)
        }
    }
}