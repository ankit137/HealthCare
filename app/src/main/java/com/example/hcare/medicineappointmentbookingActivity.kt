package com.example.hcare

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class medicineappointmentbookingActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_medicineappointmentbooking)
        val edname=findViewById<TextView>(R.id.fullnamemedicineappointment)
        val edaddress=findViewById<TextView>(R.id.addressmedicineappointment)
        val edcontact=findViewById<TextView>(R.id.contactnumbermedicineappointment)
        val edpincode=findViewById<TextView>(R.id.pincodemedicineappointment)
        val btnbooking=findViewById<Button>(R.id.bookappointmentmedicineappointment)
        val intent: Intent =getIntent()
        val priceString = intent.getStringExtra("price")
        val price = if (!priceString.isNullOrEmpty()) {
            priceString.toFloat()
        } else {
            5.0f // Default value in case "price" is null or empty
        }
        val date=intent.getStringExtra("date")
       // val time=intent.getStringExtra("time")
        btnbooking.setOnClickListener {

            val sharedPreferences=getSharedPreferences("shared_perf", MODE_PRIVATE);
            val username:String=sharedPreferences.getString("username","").toString()
            val db=Database(applicationContext,"healthcare",null,1)
            db.addorder(username,edname.text.toString(), edaddress.text.toString(),edcontact.text.toString(),edpincode.text.toString(),date.toString()," ",price.toFloat(),"medicine")
            db.removecart(username,"medicine")
            val intent= Intent(this@medicineappointmentbookingActivity,homeActivity::class.java)
            startActivity(intent)
        }
    }
}