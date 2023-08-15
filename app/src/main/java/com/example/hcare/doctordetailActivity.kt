package com.example.hcare


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ListView
import android.widget.SimpleAdapter
import android.widget.TextView
import com.example.hcare.R.layout.multi_lines

class doctordetailActivity : AppCompatActivity() {
    private val doctorDetail1: Array<Array<String>> = arrayOf(
        arrayOf("Doctor Name: Ajit Saste", "Hospital Address: Pimpri", "Exp: 5yrs", "Mobile No: 9898989898", "680"),
        arrayOf("Doctor Name: Prasad Pawar", "Hospital Address: Nigdi", "Exp: 15yrs", "Mobile No: 7898989898", "900"),
        arrayOf("Doctor Name: Swapnil Kale", "Hospital Address: Pune", "Exp: 8yrs", "Mobile No: 8898989898", "380"),
        arrayOf("Doctor Name: Ashok Panda", "Hospital Address: Katraj", "Exp: 7yrs", "Mobile No: 7798989898", "88"),
        arrayOf("Doctor Name: Deepak Deshmukh", "Hospital Address: Chinchwad", "Exp: 6yrs", "Mobile No: 9898000000", "500")
    )
    private val doctorDetail2: Array<Array<String>> = arrayOf(
        arrayOf("Doctor Name: Ajit Saste", "Hospital Address: Pimpri", "Exp: 5yrs", "Mobile No: 9898989898", "680"),
        arrayOf("Doctor Name: Prasad Pawar", "Hospital Address: Nigdi", "Exp: 15yrs", "Mobile No: 7898989898", "900"),
        arrayOf("Doctor Name: Swapnil Kale", "Hospital Address: Pune", "Exp: 8yrs", "Mobile No: 8898989898", "380"),
        arrayOf("Doctor Name: Ashok Panda", "Hospital Address: Katraj", "Exp: 7yrs", "Mobile No: 7798989898", "88"),
        arrayOf("Doctor Name: Deepak Deshmukh", "Hospital Address: Chinchwad", "Exp: 6yrs", "Mobile No: 9898000000", "500")
    )
    private val doctorDetail3: Array<Array<String>> = arrayOf(
        arrayOf("Doctor Name: Ajit Saste", "Hospital Address: Pimpri", "Exp: 5yrs", "Mobile No: 9898989898", "680"),
        arrayOf("Doctor Name: Prasad Pawar", "Hospital Address: Nigdi", "Exp: 15yrs", "Mobile No: 7898989898", "900"),
        arrayOf("Doctor Name: Swapnil Kale", "Hospital Address: Pune", "Exp: 8yrs", "Mobile No: 8898989898", "380"),
        arrayOf("Doctor Name: Ashok Panda", "Hospital Address: Katraj", "Exp: 7yrs", "Mobile No: 7798989898", "88"),
        arrayOf("Doctor Name: Deepak Deshmukh", "Hospital Address: Chinchwad", "Exp: 6yrs", "Mobile No: 9898000000", "500")
    )
    private val doctorDetail4: Array<Array<String>> = arrayOf(
        arrayOf("Doctor Name: Ajit Saste", "Hospital Address: Pimpri", "Exp: 5yrs", "Mobile No: 9898989898", "680"),
        arrayOf("Doctor Name: Prasad Pawar", "Hospital Address: Nigdi", "Exp: 15yrs", "Mobile No: 7898989898", "900"),
        arrayOf("Doctor Name: Swapnil Kale", "Hospital Address: Pune", "Exp: 8yrs", "Mobile No: 8898989898", "380"),
        arrayOf("Doctor Name: Ashok Panda", "Hospital Address: Katraj", "Exp: 7yrs", "Mobile No: 7798989898", "88"),
        arrayOf("Doctor Name: Deepak Deshmukh", "Hospital Address: Chinchwad", "Exp: 6yrs", "Mobile No: 9898000000", "500")
    )
    private val doctorDetail5: Array<Array<String>> = arrayOf(
        arrayOf("Doctor Name: Ajit Saste", "Hospital Address: Pimpri", "Exp: 5yrs", "Mobile No: 9898989898", "680"),
        arrayOf("Doctor Name: Prasad Pawar", "Hospital Address: Nigdi", "Exp: 15yrs", "Mobile No: 7898989898", "900"),
        arrayOf("Doctor Name: Swapnil Kale", "Hospital Address: Pune", "Exp: 8yrs", "Mobile No: 8898989898", "380"),
        arrayOf("Doctor Name: Ashok Panda", "Hospital Address: Katraj", "Exp: 7yrs", "Mobile No: 7798989898", "88"),
        arrayOf("Doctor Name: Deepak Deshmukh", "Hospital Address: Chinchwad", "Exp: 6yrs", "Mobile No: 9898000000", "500")
    )
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_doctordetail)

        val list: ArrayList<HashMap<String, String>> = ArrayList()
        val title = findViewById<TextView>(R.id.subheadingdoctordetail)
        val subtitle = intent.getStringExtra("title")


        title.text = subtitle




        val selectedDoctorDetail: Array<Array<String>> = when (subtitle) {
            "Family Physician" -> doctorDetail1
            "Dietician" -> doctorDetail2
            "Dentist" -> doctorDetail3
            "Surgeon" -> doctorDetail4
            "Cardiologists" -> doctorDetail5
            else -> emptyArray() // Default case
        }
        val backbutton=findViewById<Button>(R.id.backdoctordetails)
        backbutton.setOnClickListener{
            val intent=Intent(this@doctordetailActivity,finddoctorActivity::class.java)
            startActivity(intent)
        }



        for (i in 0 until selectedDoctorDetail.size) {
            val item = HashMap<String, String>()
            item["line1"] = selectedDoctorDetail[i][0]
            item["line2"] = selectedDoctorDetail[i][1]
            item["line3"] = selectedDoctorDetail[i][2]
            item["line4"] = selectedDoctorDetail[i][3]
            item["line5"] = selectedDoctorDetail[i][4]
            println("aaaaa")
            println("size="+selectedDoctorDetail.size.toString())
            list.add(item)

        }



        val st = SimpleAdapter(
            this,
            list,
            multi_lines,
            arrayOf("line1", "line2","line3","line4","line5"),
            intArrayOf(R.id.a, R.id.b, R.id.c, R.id.d, R.id.e)
        )
        val ll:ListView=findViewById(R.id.listview_doctordetail)
        ll.adapter=st

        ll.setOnItemClickListener { adapterView, view, i, l ->
            val intent=Intent(this@doctordetailActivity,bookappointmentActivity::class.java)

           intent.putExtra("text1",subtitle)
            intent.putExtra("text2",selectedDoctorDetail[i][0])
            intent.putExtra("text3",selectedDoctorDetail[i][1])
            intent.putExtra("text4",selectedDoctorDetail[i][3])
            intent.putExtra("text5",selectedDoctorDetail[i][4])
            startActivity(intent)

        }



    }


}