package com.mcornel.ecommerce

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.VolleyError
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_second.*

class SecondActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        var siteAddress = getSiteUrl()

        var resultString = intent.getStringExtra("result")
        txtResult.text = resultString
        btnSave.setOnClickListener {
            var name = txtName.text.toString()
            var fileData = "$name: $resultString"

            // using shared preferences to write to file
            var sharedPref = getSharedPreferences("my_calculations", Context.MODE_PRIVATE)  // creates file
            var editor = sharedPref.edit()
            editor.putString("result", fileData)        // add variable result with it's value
            editor.commit()
        }

        btnReadFile.setOnClickListener {
            var sharedPref = getSharedPreferences("my_calculations", Context.MODE_PRIVATE)  // creates file
            txtRead.text = sharedPref.getString("result", "Nothing found")       // use shared preference to read file or return default
        }

        btnGetProduct.setOnClickListener {
            var product_id = edProductId.text.toString()
            var url = "$siteAddress/ecom/get_one.php?table=products&id=$product_id"
            var requestQ = Volley.newRequestQueue(this)

            // Json Object request with 5 params method, url, jsonrequest, success action and error action
            var jobREquest =JsonObjectRequest(Request.Method.GET, url, null,
                Response.Listener { response ->
                    txtProductName.text = response.getString("name")
                    txtProductDescription.text = response.getString("description")
                    txtProductPrice.text = response.getString("price")
                    var productImage:String = response.getString("photo_url")
                    var imgurl = productImage.replace("http://localhost/mcornel.com",siteAddress)
                    // use picasso to place the image
                    Picasso.with(this).load(imgurl).into(ivProductImage)
                },
                Response.ErrorListener { error ->
                    txtProductDescription.text = error.message
                })
            requestQ.add(jobREquest)
        }

        btnGetAll.setOnClickListener {
            var table = txtTable.text.toString()
            var intent = Intent(this, AllItemsAct::class.java)
            intent.putExtra("table", table)
            startActivity(intent)
        }
    }
}
