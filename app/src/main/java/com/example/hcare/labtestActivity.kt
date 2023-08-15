package com.example.hcare

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ListView
import android.widget.SimpleAdapter
import kotlinx.android.synthetic.main.activity_labtest.*

class labtestActivity : AppCompatActivity() {
    val packages: Array<Array<String>> = arrayOf(
        arrayOf("Package 1 : Full Body Checkup", "","","", "1200"),
        arrayOf("Package 2 : Covid 19", "","","", "600"),
        arrayOf("Package 3 : Immunity Check", "","","", "899"),
        arrayOf("Package 4 : Stamina Check", "","","", "999"),
        arrayOf("Package 5 : Heartbeat Check", "","","", "1299")
    )
    val packagedetail: Array<Array<String>> = arrayOf(
        arrayOf(
            "Blood Glucose Fasting",
            "Complete Hemogram",
            "HbA1c",
            "Iron Studies",

        ),
        arrayOf(
            "Antigen test",
            "COVID-19 Antibody - IgG",
            "Thyroid Profile-Total (T3, T4 & TSH Ultra-sensitive)"
        ),
        arrayOf(
            "Complete Hemogram",
            "CRP (C Reactive Protein) Quantitative, Serum",
            "Iron Studies",

        ),
    arrayOf( "Kidney Function Test",
        "Vitamin D Total-25 Hydroxy",
        "Liver Function Test",
        "Lipid Profile"),
        arrayOf( "Kidney Function Test",
            "LDH Lactate Dehydrogenase, Serum",
            "Lipid Profile",
            "Liver Function Test")

    )

    override fun onCreate(savedInstanceState: Bundle?) {
        val list: ArrayList<HashMap<String, String>> = ArrayList()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_labtest)
        val cart=findViewById<Button>(R.id.gotocart)
        val back=findViewById<Button>(R.id.backlabtest)
        val listview=findViewById<ListView>(R.id.listview_labtest)
        back.setOnClickListener {
            val intent=Intent(this@labtestActivity,homeActivity::class.java)
            startActivity(intent)
        }
        for (i in 0 until packages.size) {
            val item = HashMap<String, String>()
            item["line1"] = packages[i][0]
            item["line2"] = packages[i][1]
            item["line3"] = packages[i][2]
            item["line4"] = packages[i][3]
            item["line5"] = "Total Cost:"+packages[i][4]

            list.add(item)

        }
        val st = SimpleAdapter(
            this,
            list,
            R.layout.multi_lines,
            arrayOf("line1", "line2","line3","line4","line5"),
            intArrayOf(R.id.a, R.id.b, R.id.c, R.id.d, R.id.e)
        )
        val ll:ListView=findViewById(R.id.listview_labtest)
        ll.adapter=st
        ll.setOnItemClickListener { adapterView, view, i, l ->
            val intent=Intent(this@labtestActivity,labtestdetailsActivity::class.java)
            intent.putExtra("text1",packages[i][0])
            intent.putExtra("text2",packagedetail[i][0])

            intent.putExtra("text3",packages[i][4])

            startActivity(intent)
        }
        gotocart.setOnClickListener {

            val intent2=Intent(this@labtestActivity,cartlabActivity::class.java)
            startActivity(intent2)


        }
    }
}