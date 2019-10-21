package com.mcornel.ecommerce

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_login.*

class LoginAct : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        btnSignup.setOnClickListener {
            var intent = Intent(this, SignupAct::class.java)
            startActivity(intent)
        }

        btnLogin.setOnClickListener {
            var phone = tePhone.text.toString()
            var password = tePassword.text.toString()

            val url = "${getSiteUrl()}/ecom/login.php"

            val requestQ = Volley.newRequestQueue(this)
            val loginRequest = object : StringRequest(
                Method.POST, url,
                Response.Listener { res ->
                    if (res == "1") {
                        Toast.makeText(this, "Successfully Login", Toast.LENGTH_LONG).show()
                        UserInfo.mobile = phone
                        var intent = Intent(this, HomeAct::class.java)
                        intent.putExtra("name", phone)
                        startActivity(intent)
                    } else {
                        Toast.makeText(this, "Not Login", Toast.LENGTH_LONG).show()
                    }
                },
                Response.ErrorListener { err ->
                    Toast.makeText(this, "Error: ${err.message}", Toast.LENGTH_LONG).show()
                }
            ) {
                override fun getParams(): MutableMap<String, String> {
                    val parcel = HashMap<String, String>()
                    parcel["mobile"] = phone
                    parcel["password"] = password
                    return parcel
                }
            }
            requestQ.add(loginRequest)
        }
    }
}
