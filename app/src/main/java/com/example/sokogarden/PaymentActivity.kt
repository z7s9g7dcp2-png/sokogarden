package com.example.sokogarden

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.bumptech.glide.Glide
import com.loopj.android.http.RequestParams

class PaymentActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_payment2)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
//        Finf the views by use of their ids
        val txtname = findViewById<TextView>(R.id.txtProductname)
        val textCost = findViewById<TextView>(R.id.txtProductcost)
        val imgProduct = findViewById<ImageView>(R.id.imageProduct)

//        Retrieve the data passed from the previous activity
        val name = intent.getStringExtra("product_name")
        val cost = intent.getIntExtra("product_cost",0)
        val product_photo = intent.getStringExtra("product_photo")


//        Update the textView with the data passed from thr previous activity
        txtname.text = name
        textCost.text ="Ksh $cost"

        // specify the image url
        val imageurl = "https://leylahniyasmin.alwaysdata.net/static/images/$product_photo"

        //Load image using Glide, Load Faster with Glide
        Glide.with(this)
            .load(imageurl )
            .placeholder(R.drawable.ic_launcher_background) // Make sure you have a placeholder image
            .into(imgProduct)

        //find the edittext and the  pay now button by use of thier ids
        val phone = findViewById<EditText>(R.id.Phone)
        val btnpay = findViewById<Button>(R.id.payment)

        // set on click listener
        btnpay.setOnClickListener {
            // specify the Api endpoint for making payment
            val api = "http://leylahniyasmin.alwaysdata.net/api/mpesa_payment"

            //Create a requestParams
            val data = RequestParams()

            // insert data into the request params
            data.put("amount",cost)
            data.put("phone",phone.text.toString().trim())

            // import thehelper class
            val helper = ApiHelper(applicationContext)

            // access the post function inside the helper class
            helper.post(api,data)

            phone.text.clear()

        }
    }
}