package com.example.hcare

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.provider.MediaStore.Images
import android.widget.Button
import android.widget.ListView
import android.widget.SimpleAdapter
import kotlinx.android.synthetic.main.activity_home.*

class healthArticlesActivity : AppCompatActivity() {
    private val health_details: Array<Array<String>> = arrayOf(
        arrayOf("Walking Daily", "", "", "", "Click for more details"),
        arrayOf("Homecare of Covid 19", "", "", "", "Click for more details"),
        arrayOf("Stop Smoking", "", "", "", "Click for more details"),
        arrayOf("Menstrual Cramps", "", "", "", "Click for more details"),
        arrayOf("Healthygut", "", "", "", "Click for more details")
    )
    private val images: Array<Int> = arrayOf(
        R.drawable.health1,
        R.drawable.health2,
        R.drawable.health3,
        R.drawable.health4,
        R.drawable.health5
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_health_articles)
        val backbutton = findViewById<Button>(R.id.backhealtharticles)
        backbutton.setOnClickListener {
            val intent = Intent(this@healthArticlesActivity, homeActivity::class.java)
            startActivity(intent)
        }
        val list: ArrayList<HashMap<String, String>> = ArrayList()
        for (i in 0 until health_details.size) {
            val item = HashMap<String, String>()
            item["line1"] = health_details[i][0]
            item["line2"] = health_details[i][1]
            item["line3"] = health_details[i][2]
            item["line4"] = health_details[i][3]
            item["line5"] = health_details[i][4]
            list.add(item)

        }
        val st = SimpleAdapter(
            this,
            list,
            R.layout.multi_lines,
            arrayOf("line1", "line2", "line3", "line4", "line5"),
            intArrayOf(R.id.a, R.id.b, R.id.c, R.id.d, R.id.e)
        )
        val ll: ListView = findViewById(R.id.listview_healtharticles)
        ll.adapter = st
        ll.setOnItemClickListener { adapterView, view, i, l ->
            val intent=Intent(this@healthArticlesActivity,healthArticlesDetailsActivity::class.java)

            intent.putExtra("text1",health_details[i][0])
            intent.putExtra("text2",images[i])

            startActivity(intent)

        }
    }
}