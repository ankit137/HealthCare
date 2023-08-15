package com.example.hcare

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import java.util.*

class bookappointmentActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bookappointment)

        val title=findViewById<TextView>(R.id.titlebookappointment)
        val fullname=findViewById<TextView>(R.id.fullname)
        val address=findViewById<TextView>(R.id.address)
        val contactnumber=findViewById<TextView>(R.id.contactnumber)
        val fees=findViewById<TextView>(R.id.fees)
        fullname.keyListener=null
        address.keyListener=null
        contactnumber.keyListener=null
        fees.keyListener=null
 val intent:Intent=getIntent()
        title.text=intent.getStringExtra("text1")
        fullname.text=intent.getStringExtra("text2")
        address.text=intent.getStringExtra("text3")
        contactnumber.text=intent.getStringExtra("text4")
        fees.text=intent.getStringExtra("text5")

        val datePickerDialog=findViewById<Button>(R.id.date)
        val timePickerDialog=findViewById<Button>(R.id.time)
        val calendar = Calendar.getInstance()

        datePickerDialog.setOnClickListener {
            // Create a DatePickerDialog with the current date as the default selection
            val datePicker = DatePickerDialog(
                this,
                { _, year, monthOfYear, dayOfMonth ->
                    // Handle the selected date
                    val selectedDate = "$dayOfMonth/${monthOfYear + 1}/$year"
                    datePickerDialog.text = selectedDate
                },
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)
            )

            // Show the DatePickerDialog
            datePicker.show()
        }
        timePickerDialog.setOnClickListener {
            // Create a TimePickerDialog with the current time as the default selection
            val timePicker = TimePickerDialog(
                this,
                { _, hourOfDay, minute ->
                    // Handle the selected time
                    val selectedTime = "$hourOfDay:$minute"
                    timePickerDialog.text = selectedTime
                },
                calendar.get(Calendar.HOUR_OF_DAY),
                calendar.get(Calendar.MINUTE),
                false // Use 24-hour format
            )

            // Show the TimePickerDialog
            timePicker.show()
        }
        val bookbutton=findViewById<Button>(R.id.bookappointment)
        val backbutton=findViewById<Button>(R.id.backbutton)
        backbutton.setOnClickListener{
            val intent=Intent(this@bookappointmentActivity,finddoctorActivity::class.java)
            startActivity(intent)
        }
        bookbutton.setOnClickListener{
            val db=Database(applicationContext,"healthcare",null,1)
            val sharedPreferences=getSharedPreferences("shared_perf", MODE_PRIVATE);
            val username:String=sharedPreferences.getString("username","").toString()
            if (db.checkAppointmentExists(username.toString(),title.text.toString() +"=>"+ fullname.text.toString(),address.text.toString(),contactnumber.text.toString(),datePickerDialog.text.toString(),timePickerDialog.text.toString())==1)
                 {
                    Toast.makeText(applicationContext, "Already booked", Toast.LENGTH_SHORT).show()
                }
                else
                {
                    db.addorder(username.toString(),title.text.toString() +"=>"+ fullname.text.toString(),address.text.toString(),contactnumber.text.toString(),"",datePickerDialog.text.toString(),timePickerDialog.text.toString(),fees.text.toString().toFloat(),"appointment")
                    Toast.makeText(applicationContext, "Successfully booked", Toast.LENGTH_SHORT).show()
                }
            val intent=Intent(this@bookappointmentActivity,homeActivity::class.java)
            startActivity(intent)
            }
        }
    }




