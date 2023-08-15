package com.example.hcare

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_labtest.*
import kotlinx.android.synthetic.main.activity_labtestdetails.*
import java.lang.Float

class labtestdetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_labtestdetails)
        val packagename = findViewById<TextView>(R.id.labtestandpackageslabdetails)
        val cost = findViewById<TextView>(R.id.textView2)
        val details = findViewById<TextView>(R.id.listview_labtestdetails)
        val addtocart=findViewById<Button>(R.id.addtocart)
val back=findViewById<Button>(R.id.backlabtestdetails)
        val intent: Intent = getIntent()
        val packageName = intent.getStringExtra("text1")
        details.setText(intent.getStringExtra("text2"))
         cost.setText("Total Cost:" +intent.getStringExtra("text3"))

        packagename.text = packageName



        back.setOnClickListener {
            val intent=Intent(this@labtestdetailsActivity,labtestActivity::class.java)
            startActivity(intent)
        }
        addtocart.setOnClickListener {
            val sharedPreferences=getSharedPreferences("shared_perf", Context.MODE_PRIVATE);
            val username:String=sharedPreferences.getString("username","").toString()
            val product:String=packageName.toString()
            val price= intent.getStringExtra("text3")

            val db:Database=Database(applicationContext,"healthcare",null,1)
            if(db.checkcart(username, product)==1){
                Toast.makeText(applicationContext,"Product alreaady exist",Toast.LENGTH_SHORT).show()

            }else{

                    db.addtocart(username,product, price.toString(),"lab" )


                Toast.makeText(applicationContext,"Product added to cart ",Toast.LENGTH_SHORT).show()

            }
        }


}}