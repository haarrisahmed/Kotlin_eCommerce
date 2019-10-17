package com.mcornel.ecommerce

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var siteAddress = getSiteUrl()

        fun calculate(): String {
            var num1 = txtNum1.text.toString().toFloat()
            var num2 = txtNum2.text.toString().toFloat()
            var sum = num1 + num2
            return "$num1 + $num2 = $sum"
        }

        btnAdd.setOnClickListener {

            Toast.makeText(this, calculate(), Toast.LENGTH_LONG).show()

        }
        rbRed.setOnClickListener {
            screen.setBackgroundColor(Color.rgb(255, 0, 0))
        }
        rbGreen.setOnClickListener {
            screen.setBackgroundColor(Color.parseColor("green"))
        }
        rbBlue.setOnClickListener {
            //            screen.setBackgroundColor(Color.parseColor("#ffOO00ff")) todo figure out why this format fails
            screen.setBackgroundColor(Color.rgb(0, 0, 255))
        }

        btnSecondAct.setOnClickListener {
            var intent = Intent(this, SecondActivity::class.java)
            intent.putExtra("result", calculate())
            startActivity(intent)
        }

        btnGetServerText.setOnClickListener {
            var requestQ = Volley.newRequestQueue(this)    // instance of volley request

            var url = "$siteAddress/ecom/"
            // String request with 4 params: method, url, action for success and action for error
            var strRequest = StringRequest(Request.Method.GET, url,
                Response.Listener { response ->
                    txtWelcome.text = response

                },
                Response.ErrorListener { error ->
                    txtWelcome.text = "ERROR: ${error.message}"
                })

            // make connection between the request and the result
            requestQ.add(strRequest)
        }
    }
}
