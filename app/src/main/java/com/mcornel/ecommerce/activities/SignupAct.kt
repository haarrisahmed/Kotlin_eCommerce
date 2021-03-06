package com.mcornel.ecommerce.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.mcornel.ecommerce.R
import com.mcornel.ecommerce.models.UserInfo
import com.mcornel.ecommerce.getSiteUrl
import kotlinx.android.synthetic.main.activity_signup.*
import org.json.JSONObject

class SignupAct : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        val siteUrl = getSiteUrl()
        val url = "$siteUrl/ecom/signup.php"

        btnSignup.setOnClickListener {
            val name = teName.text.toString()
            val phone = tePhone.text.toString()
            val address = teAddress.text.toString()
            val password = tePassword.text.toString()
            val password2 = tePassword2.text.toString()

            if (password == password2) {
                // package and send request Note this is a post request hence the changes from previous get in main activity
                val requestQ = Volley.newRequestQueue(this)
                val signupRequest = object:StringRequest(Method.POST, url,
                    Response.Listener { res ->
//                        stuff to do on success
                        if(res == "0") {
                            Toast.makeText(this, "Mobile number already in use.", Toast.LENGTH_LONG).show()
                        }
                        else {
                            Toast.makeText(this, "Successful signup", Toast.LENGTH_LONG).show()
                            val user = JSONObject(res)
                            UserInfo.mobile = user.getString("mobile")
                            UserInfo.id = user.getInt("id")
                            UserInfo.json = user

                            startActivity(Intent(this, HomeAct::class.java))
                        }
                    },
                    Response.ErrorListener { err ->
//                        stuff for error
                        Toast.makeText(this, "Error: ${err.message}", Toast.LENGTH_LONG).show()

                    })
                {
                    override fun getParams(): MutableMap<String, String> {
                        var parcel = HashMap<String, String>()
                        parcel.put("name", name)
                        parcel.put("address", address)
                        parcel.put("password", password)
                        parcel.put("mobile", phone)
                        return parcel
                    }
                }
                requestQ.add(signupRequest)
            }
            else {
//                Do somthing when they don't match
                Toast.makeText(this, "Password did not match.", Toast.LENGTH_LONG).show()

            }

        }
    }
}
