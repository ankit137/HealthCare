package com.example.hcare

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ListView
import android.widget.SimpleAdapter
import kotlinx.android.synthetic.main.multi_lines.*

class orderDetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order_details)
        val back=findViewById<Button>(R.id.orderdetailback)
        val ll=findViewById<ListView>(R.id.listview_orderdetail)
        back.setOnClickListener {
          val inten=  Intent(this@orderDetailsActivity,homeActivity::class.java)
        startActivity(inten)
        }
        val sharedPreferences=getSharedPreferences("shared_perf", Context.MODE_PRIVATE);
        val username:String=sharedPreferences.getString("username","").toString()
        val db:Database=Database(applicationContext,"healthcare",null,1)
        val dbData: ArrayList<String> = db.getOrderData(username)
        val orderDetails = Array(dbData.size) { arrayOfNulls<String>(5) }

        for (i in orderDetails.indices) {
            val arrData = dbData[i].toString()
            val strData = arrData.split("$")
            orderDetails[i][0] = strData[0]
            orderDetails[i][1] = strData[1]

            if (strData[7] == "medicine") {
                orderDetails[i][3] = "Date=${strData[4]}"
            } else {
                orderDetails[i][3] = "Date=${strData[4]} Time=${strData[5]}"
            }

            orderDetails[i][2] = "Rs.${strData[6]}"
            orderDetails[i][4] = strData[7]
        }
        val list = ArrayList<HashMap<String, String>>()
        for (i in orderDetails.indices) {
            val item = HashMap<String, String>()
            item["line1"] = orderDetails[i][0]?: ""
            item["line2"] = orderDetails[i][1]?: ""
            item["line3"] = orderDetails[i][2]?: ""
            item["line4"] = orderDetails[i][3]?: ""
            item["line5"] = orderDetails[i][4]?: ""
            list.add(item)
        }

        val adapter = SimpleAdapter(
            this,
            list,
            R.layout.multi_lines,
            arrayOf("line1", "line2", "line3", "line4", "line5"),
            intArrayOf(R.id.a, R.id.b, R.id.c, R.id.d, R.id.e)
        )

        ll.adapter = adapter

    }
}