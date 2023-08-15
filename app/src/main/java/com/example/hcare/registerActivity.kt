package com.example.hcare

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_login.view.*

class registerActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        val usernameregister:EditText=findViewById(R.id.edittextloginusernameregister);
        var email:EditText=findViewById(R.id.email);
        var passwordregister:EditText=findViewById(R.id.edittextpasswordregister);
        var passwordregisterconfirm:EditText=findViewById(R.id.edittextpasswordregisterconfirm);
        var registerbutton: Button =findViewById(R.id.loginbuttonregister);
        var already:TextView  =findViewById(R.id.alreadyaccount);
        already.setOnClickListener{
            val  intent= Intent(this@registerActivity,loginActivity::class.java);
            startActivity(intent);
        }
        registerbutton.setOnClickListener{
           val username=usernameregister.text;
val passwordconfirm=passwordregisterconfirm.text;
            val password=passwordregister.text;
            val email=email.text;
            val db = Database(applicationContext,"healthcare",null,1)
            if((usernameregister.length() == 0) || (passwordregister.length() == 0)||passwordregister.length()==0||passwordregisterconfirm.length()==0)
            {
                val message = "Please Enter Details"
                val duration = Toast.LENGTH_SHORT

                val toast = Toast.makeText(this, message, duration)
                toast.show()
            }else{
                 if(passwordregister.text.toString()!=passwordregisterconfirm.text.toString()){

                    val message = "password error";
                    val duration = Toast.LENGTH_SHORT

                    val toast = Toast.makeText(this, message, duration)
                    toast.show()
                }
                 else{
                     try {
                         db.register(username.toString(), email.toString(), password.toString())
                         // Registration successful, perform any necessary actions
                     } catch (e: Exception) {
                         e.printStackTrace() // Print the exception for debugging purposes
                         // Handle the exception, show an error message, etc.
                     }
                     val message = "Success"
                     val duration = Toast.LENGTH_SHORT

                     val toast = Toast.makeText(this, message, duration)
                     toast.show()
                     val  intent=Intent(this@registerActivity,loginActivity::class.java);
                     startActivity(intent);
                 }

            }




        }
    }
}