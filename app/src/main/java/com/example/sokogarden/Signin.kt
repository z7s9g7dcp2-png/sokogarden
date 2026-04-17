package com.example.sokogarden

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.loopj.android.http.RequestParams

class Signin : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_signin)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

//        Find the two edit text a button and textviews by use of their ids
        val email = findViewById<EditText>(R.id.email)
        val password = findViewById<EditText>(R.id.password)
        val signinbutton =findViewById<Button>(R.id.signinBtn)
        val signupTextView = findViewById<TextView>(R.id.signuptxt)

//        on the textview set onclick listener such that when clicked it navigates you to the signup page
        signupTextView.setOnClickListener {
            val intent = Intent(applicationContext, Signup::class.java)
            startActivity(intent)
        }
// on click the button signin,we need to interactwith out api endpoint as we pass the two data info email and password
        signinbutton.setOnClickListener {


//        Specify the API endpoint
            val api = "https://leylahniyasmin.alwaysdata.net/api/signin"

// Create a Requestparams the will
            val data = RequestParams()

//        Add/append/attach the email and the password
            data.put("email",email.text.toString())
            data.put("password",password.text.toString())

//        import the api helper
            val helper = ApiHelper(applicationContext)

//        By use of the function post_login inside of the helper class,post your data
            helper.post_login(api, data)


            email.text.clear()
            password.text.clear()


        }

    }
}