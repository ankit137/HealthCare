package com.example.hcare

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.cardview.widget.CardView
import kotlinx.android.synthetic.main.activity_home.*

class homeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        val sharedPreferences=getSharedPreferences("shared_perf", Context.MODE_PRIVATE);
        val username= sharedPreferences.getString("username","").toString();
        Toast.makeText(applicationContext,"welcome"+username,Toast.LENGTH_SHORT).show()
val exit=findViewById<CardView>(R.id.exit)
        val healtharticles=findViewById<CardView>(R.id.healthArticles)
        exit.setOnClickListener{
            val editor = sharedPreferences.edit();
            editor.clear()
            editor.apply()

            val intent=Intent(this@homeActivity,loginActivity::class.java)
            startActivity(intent)
        }
        val finddoctor=findViewById<CardView>(R.id.finddoctor)
        finddoctor.setOnClickListener{

            val intent=Intent(this@homeActivity,finddoctorActivity::class.java)
            startActivity(intent)
        }
        val labtest=findViewById<CardView>(R.id.labtest)
        labtest.setOnClickListener{

            val intent=Intent(this@homeActivity,labtestActivity::class.java)
            startActivity(intent)
        }
        val orderdetails=findViewById<CardView>(R.id.orderdetailshome)
        orderdetails.setOnClickListener{

            val intent=Intent(this@homeActivity,orderDetailsActivity::class.java)
            startActivity(intent)
        }
        buymedicine.setOnClickListener{
            val intent=Intent(this@homeActivity,buymedicineActivity::class.java)
            startActivity(intent)
        }

        healtharticles.setOnClickListener{
            val intent=Intent(this@homeActivity,healthArticlesActivity::class.java)
            startActivity(intent)
        }
    }
}