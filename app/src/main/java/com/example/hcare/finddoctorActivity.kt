package com.example.hcare

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.cardview.widget.CardView
import kotlinx.android.synthetic.main.activity_doctordetail.*

class finddoctorActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_finddoctor)
        val exit=findViewById<CardView>(R.id.exitfinddoctor)
        val familyphysician=findViewById<CardView>(R.id.familyphysician)
        val dietician=findViewById<CardView>(R.id.dietician)
        val dentist=findViewById<CardView>(R.id.dentist)
        val surgeon=findViewById<CardView>(R.id.surgeon)
        val cardiologists=findViewById<CardView>(R.id.cardiologist)
        exit.setOnClickListener{
            val intent=Intent(this@finddoctorActivity,homeActivity::class.java)
            startActivity(intent)
        }
        familyphysician.setOnClickListener{
            val intent=Intent(this@finddoctorActivity,doctordetailActivity::class.java)
            intent.putExtra("title","Family Physician")

            startActivity(intent)
        }


        dietician.setOnClickListener{
            val intent=Intent(this@finddoctorActivity,doctordetailActivity::class.java)
            intent.putExtra("title","Dietician")
            startActivity(intent)
        }
        dentist.setOnClickListener{
            val intent=Intent(this@finddoctorActivity,doctordetailActivity::class.java)
            intent.putExtra("title","Dentist")
            startActivity(intent)
        }
        surgeon.setOnClickListener{
            val intent=Intent(this@finddoctorActivity,doctordetailActivity::class.java)
            intent.putExtra("title","Surgeon")
            startActivity(intent)
        }
        cardiologists.setOnClickListener{
            val intent=Intent(this@finddoctorActivity,doctordetailActivity::class.java)
            intent.putExtra("title","Cardiologists")
            startActivity(intent)
        }

    }
}