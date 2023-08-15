package com.example.hcare

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_login.*

class loginActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        var username:EditText=findViewById(R.id.edittextloginusername);
        var password:EditText=findViewById(R.id.edittextpassword);
        var loginbutton:Button=findViewById(R.id.loginbutton);
        var newuser:TextView=findViewById(R.id.registernewuser);
        newuser.setOnClickListener{

          val  intent= Intent(this@loginActivity,registerActivity::class.java);
            startActivity(intent);
        }

        loginbutton.setOnClickListener{
            val db = Database(applicationContext,"healthcare",null,1)
            if((username.length() == 0) || (password.length() == 0))
            {
                val message = "Please Enter Details"
                val duration = Toast.LENGTH_LONG

                val toast = Toast.makeText(this, message, duration)
                toast.show()
            }
            else{

                if(db.login(username.text.toString(),password.text.toString())==1){
                    println("aaaaaaaaaaa")
                    println(username)
                val message = "Login Success"
                val duration = Toast.LENGTH_SHORT

                val toast = Toast.makeText(this, message, duration)
                toast.show()
                    val  intent=Intent(this@loginActivity,homeActivity::class.java);
                    startActivity(intent)
                    val sharedPreferences=getSharedPreferences("shared_perf",Context.MODE_PRIVATE);
                    val editor = sharedPreferences.edit();
                    editor.putString("username",username.toString());
                    editor.apply();


                }
                else
                {
                    val message = "credentials don't match"
                    val duration = Toast.LENGTH_SHORT

                    val toast = Toast.makeText(this, message, duration)
                    toast.show()

                }
            }

        }



    }
}