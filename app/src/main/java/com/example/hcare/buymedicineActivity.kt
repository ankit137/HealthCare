package com.example.hcare

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ListView
import android.widget.SimpleAdapter
import kotlinx.android.synthetic.main.activity_labtest.*

class buymedicineActivity : AppCompatActivity() {
    val packages: Array<Array<String>> = arrayOf(
        arrayOf("Uprise D3", "","","", "1200"),
        arrayOf("HEalthvit", "","","", "600"),
        arrayOf("Crocin", "","","", "899"),
        arrayOf("PAracetamol", "","","", "999"),
        arrayOf("Calcium", "","","", "1299"),
        arrayOf("Iron", "","","", "1299"),
        arrayOf("Skincare", "","","", "1299"),
        arrayOf("Vitamin D", "","","", "1299")

    )
    val packagedetail: Array<Array<String>> = arrayOf(
        arrayOf("Uprise D3\n\nUprise D3 is a vitamin D supplement that helps in maintaining healthy bones and teeth. It is essential for calcium absorption and promoting overall bone health."

            ),
        arrayOf(
            "Healthvit\n\nHealthvit offers a range of health supplements to support your well-being. From vitamins to minerals, Healthvit provides essential nutrients for maintaining good health."
        ),
        arrayOf(
            "Crocin\n\nCrocin is a popular over-the-counter medicine used to reduce pain and fever. It provides relief from headaches, muscle pain, and other minor discomforts."
            ),
        arrayOf( "Paracetamol 500mg Tablet \n Reduces pain and fever \n Provides relief from headaches, muscle pain, and toothache"),
        arrayOf( "Calcium\n\nCalcium is an essential mineral that supports strong bones and teeth. It plays a vital role in maintaining bone density and preventing osteoporosis."),
        arrayOf(
            "Iron\n\nIron is crucial for the production of hemoglobin, a protein in red blood cells that carries oxygen throughout the body. Iron supplements help prevent iron deficiency anemia."
        ),
        arrayOf(
            "Skincare\n\nSkincare products are designed to promote healthy skin and address various skin concerns. They may include creams, lotions, serums, and other formulations."

            ),
        arrayOf(  "Vitamin D\n\nVitamin D is essential for maintaining strong bones, supporting the immune system, and regulating calcium levels. Adequate vitamin D levels are important for overall health.")

    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_buymedicine)

        val list: ArrayList<HashMap<String, String>> = ArrayList()
        val cart=findViewById<Button>(R.id.gotocartmedicine)
        val back=findViewById<Button>(R.id.backmedicine)

        back.setOnClickListener {
            val intent= Intent(this@buymedicineActivity,homeActivity::class.java)
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
        val ll: ListView =findViewById(R.id.listview_medicine)
        ll.adapter=st
        ll.setOnItemClickListener { adapterView, view, i, l ->
            val intent= Intent(this@buymedicineActivity,buymedicinedetailsActivity::class.java)
            intent.putExtra("text1",packages[i][0])
            intent.putExtra("text2",packagedetail[i][0])
            intent.putExtra("text3",packages[i][4])

            startActivity(intent)
        }
        cart.setOnClickListener {

            val intent2= Intent(this@buymedicineActivity,cartbuymedicineActivity::class.java)
            startActivity(intent2)


        }

    }
}