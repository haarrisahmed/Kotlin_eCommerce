package com.mcornel.ecommerce.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.mcornel.ecommerce.R
import com.mcornel.ecommerce.models.UserInfo
import com.mcornel.ecommerce.getSiteUrl
import kotlinx.android.synthetic.main.activity_login.*
import org.json.JSONObject

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
                    if (res != "0") {
                        val user = JSONObject(res)
                        Toast.makeText(this, user.getString("mobile"), Toast.LENGTH_LONG).show()
                        UserInfo.mobile = user.getString("mobile")
                        UserInfo.id = user.getInt("id")
                        UserInfo.json = user                        // store the whole of the json data about user from db

                        val intent = Intent(this, HomeAct::class.java)
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
