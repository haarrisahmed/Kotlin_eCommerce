package com.mcornel.ecommerce

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_signup.*

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
                        Toast.makeText(this, res, Toast.LENGTH_LONG).show()
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

            }

        }
    }
}
