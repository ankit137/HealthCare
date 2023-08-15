package com.example.hcare

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import android.widget.QuickContactBadge
import android.widget.Toast


class Database(
    context: Context?,
    name: String?,
    factory: SQLiteDatabase.CursorFactory?,
    version: Int
) : SQLiteOpenHelper(context, name, factory, version) {

    override fun onCreate(p0: SQLiteDatabase?) {
        val qry1 = "CREATE TABLE users(username text, email text, password text)"
        try {
            p0?.execSQL(qry1)
            println("Table 'users' created successfully.")
        } catch (e: Exception) {
            println("Error creating 'users' table: ${e.message}")
        }
        val qry2 = "CREATE TABLE cart(username text, product text, price float,otype text)"
        try {
            p0?.execSQL(qry2)
            println("Table 'users' created successfully.")
        } catch (e: Exception) {
            println("Error creating 'users' table: ${e.message}")
        }
        val qry3 = "CREATE TABLE orderplace(username text,fullname text,address text,phonenumber text, pincode text, date text,time text,amount float,otype text)"
        try {
            p0?.execSQL(qry3)
            println("Table 'users' created successfully.")
        } catch (e: Exception) {
            println("Error creating 'users' table: ${e.message}")
        }
    }


    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {

    }
    public fun register(username:String,email:String,password:String) {
       val cv=ContentValues();
        cv.put("username",username)
        cv.put("email",email)
        cv.put("password",password)
        val db=writableDatabase
        db.insert("users",null,cv)
        db.close()

    }
    public fun login(username: String, password: String): Int {

        var result = 0
        val db = readableDatabase
        val query = "SELECT * FROM users WHERE username = ? AND password = ?"


            val cursor = db.rawQuery(query,arrayOf(username,password))


            if (cursor.moveToFirst()) {
                result = 1

            }
            cursor.close()



        return result
    }
    fun addtocart(username: String, product: String, price: String, otype: String) {
        val cv = ContentValues()
        cv.put("username", username)
        cv.put("product", product)
        cv.put("price", price)
        cv.put("otype", otype)

        val db = writableDatabase
        db.insert("cart", null, cv)
        db.close()
    }
    public fun addorder(
        username: String,
        fullname: String,
        address:String,
        phonenumber:String,
        pincode:String,
        date:String,
        time:String,
        price: Float,
        otype:String){
        val cv = ContentValues()
        cv.put("username", username)
        cv.put("fullname", fullname)
        cv.put("address", address)
        cv.put("phonenumber", phonenumber)
        cv.put("pincode", pincode)
        cv.put("date",date)
        cv.put("time",time)
        cv.put("amount",price)
        cv.put("otype", otype)

        val db = writableDatabase
        db.insert("orderplace", null, cv)
        db.close()
    }
    public fun checkcart(username: String,product: String): Int {
        var result:Int=0
        val str = arrayOf("", "")
        str[0]=username
        str[1]=product
        val db:SQLiteDatabase = readableDatabase
        val c:Cursor=db.rawQuery("select * from cart where username = ? and product = ?",str)
        if(c.moveToFirst()){
            result=1
        }
        db.close()
        return result
    }
    public fun removecart(username: String,otype: String) {
        var result:Int=0
        val str = arrayOf("", "")
        str[0]=username
        str[1]=otype
        val db:SQLiteDatabase = writableDatabase
        db.delete("cart","username=? and otype=?",str)
    }
    public fun getOrderData(username: String):ArrayList<String>{
        val arr :ArrayList<String> = ArrayList()
        val db:SQLiteDatabase=readableDatabase
        val str= arrayOf("")
        str[0]=username
        val c:Cursor=db.rawQuery("select * from orderplace where username = ?",str)
        if(c.moveToFirst()){
            do{
              arr.add(c.getString(1)+"$"+c.getString(2)+"$"+c.getString(3)+"$"+c.getString(4)+"$"+c.getString(5)+"$"+c.getString(6)+"$"+c.getString(7)+"$"+c.getString(8))
            }while (c.moveToNext())
        }
        db.close()
        return arr
    }
    public fun getCartData(username: String,product: String): ArrayList<String> {
        val arr: ArrayList<String> = ArrayList()
        val str = arrayOf("", "")
        str[0]=username
        str[1]=product
        val db:SQLiteDatabase = readableDatabase
        val c:Cursor=db.rawQuery("select * from cart where username = ? and otype = ?",str)
        if(c.moveToFirst()){
           do{
               val product:String=c.getString(1)
                val price:String=c.getString(2)
               arr.add(product+"$"+price)
           }while (c.moveToNext())
        }
        c.close()
        db.close()

        return arr
    }
    public fun checkAppointmentExists(username: String,fullname: String,address: String,phonenumber: String,date: String,time: String): Int {
        var result:Int=0;
        val str = arrayOf("", "","","","","")
        str[0]=username
        str[1]=fullname
        str[2]=address
        str[3]=phonenumber
        str[4]=date
        str[5]=time

        val db:SQLiteDatabase = readableDatabase
        val c:Cursor=db.rawQuery("select * from orderplace where username = ? and fullname = ? and address = ? and phonenumber = ? and date = ? and time = ? ",str)
        if(c.moveToFirst()){
          result=1
        }

        db.close()
        return result
    }

}