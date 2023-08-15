package com.example.hcare

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_bookappointment.*

class labappointmentbookingActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_labappointmentbooking)
        val edname=findViewById<TextView>(R.id.fullnamelabappointment)
        val edaddress=findViewById<TextView>(R.id.addresslabappointment2)
        val edcontact=findViewById<TextView>(R.id.contactnumberlabappointment)
        val edpincode=findViewById<TextView>(R.id.pincodelabappointment)
        val btnbooking=findViewById<Button>(R.id.bookappointmentlabappointment)
        val intent:Intent=getIntent()
        val priceString = intent.getStringExtra("price")
        val price = if (!priceString.isNullOrEmpty()) {
            priceString.toFloat()
        } else {
            5.0f // Default value in case "price" is null or empty
        }
        val date=intent.getStringExtra("date")
        val time=intent.getStringExtra("time")
btnbooking.setOnClickListener {

    val sharedPreferences=getSharedPreferences("shared_perf", MODE_PRIVATE);
    val username:String=sharedPreferences.getString("username","").toString()
    val db=Database(applicationContext,"healthcare",null,1)
db.addorder(username,edname.text.toString(), edaddress.text.toString(),edcontact.text.toString(),edpincode.text.toString(),date.toString(),time.toString(),price.toFloat(),"lab")
    db.removecart(username,"lab")
    val intent=Intent(this@labappointmentbookingActivity,homeActivity::class.java)
    startActivity(intent)
}
    }
}