package com.example.hcare

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ListView
import android.widget.SimpleAdapter
import android.widget.TextView
import java.util.*
import kotlin.collections.ArrayList

class cartbuymedicineActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cartbuymedicine)
        val backbutton = findViewById<Button>(R.id.backmedicinecart)
        val datePickerDialog = findViewById<Button>(R.id.datemedicinecart)

        val calendar = Calendar.getInstance()
        val totalcost = findViewById<TextView>(R.id.feesmedicinecart)
        val ll: ListView = findViewById(R.id.listview_medicinecart)
        val checkout = findViewById<Button>(R.id.checkoutmedicinecart)
        val sharedPreferences = getSharedPreferences("shared_perf", Context.MODE_PRIVATE)
        val username: String = sharedPreferences.getString("username", "").toString()
        val db: Database = Database(applicationContext, "healthcare", null, 1)
        var fees: Float = 0.0f // Declare fees as a Float
        val dbdata: ArrayList<String> = db.getCartData(username, "medicine")
        val packages: Array<Array<String>> = Array(dbdata.size) { Array(5) { "" } }

        for (i in 0 until dbdata.size) {
            val arrdata: String = dbdata[i]
            val strdata: List<String> = arrdata.split("\\$".toRegex())

            packages[i][0] = strdata[0]
            packages[i][4] = "Cost : " + strdata[1]

            val priceValue: Float = strdata[1].toFloatOrNull() ?: 0.0f
            fees += priceValue
        }

        totalcost.text = "$fees" // Convert fees to a string and set the text
        val list: ArrayList<HashMap<String, String>> = ArrayList()

        for (i in 0 until packages.size) {
            val item = HashMap<String, String>()
            item["line1"] = packages[i][0]
            item["line2"] = packages[i][1]
            item["line3"] = packages[i][2]
            item["line4"] = packages[i][3]
            item["line5"] = packages[i][4]
            list.add(item)
        }

        val st = SimpleAdapter(
            this,
            list,
            R.layout.multi_lines,
            arrayOf("line1", "line2", "line3", "line4", "line5"),
            intArrayOf(R.id.a, R.id.b, R.id.c, R.id.d, R.id.e)
        )

        ll.adapter = st

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


        backbutton.setOnClickListener {
            val intent = Intent(this@cartbuymedicineActivity, buymedicineActivity::class.java)
            startActivity(intent)
        }
        checkout.setOnClickListener {
            val intent = Intent(this@cartbuymedicineActivity, medicineappointmentbookingActivity::class.java)
            intent.putExtra("price",totalcost.text.toString());
            intent.putExtra("date",datePickerDialog.text)


            startActivity(intent)
        }
    }
}