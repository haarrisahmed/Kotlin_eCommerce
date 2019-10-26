package com.mcornel.ecommerce.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.mcornel.ecommerce.R
import com.mcornel.ecommerce.getSiteUrl
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_second.*

class SecondActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        val siteAddress = getSiteUrl()
        val resultString = intent.getStringExtra("result")

        txtResult.text = resultString

        btnSave.setOnClickListener {
            val name = txtName.text.toString()
            val fileData = "$name: $resultString"

            // using shared preferences to write to file
            val sharedPref = getSharedPreferences("my_calculations", Context.MODE_PRIVATE)  // creates file
            val editor = sharedPref.edit()
            editor.putString("result", fileData)        // add variable result with it's value
//            editor.commit()                           // commit writes the data into storage immediately...apply does it in background
            editor.apply()
        }

        btnReadFile.setOnClickListener {
            val sharedPref =
                getSharedPreferences("my_calculations", Context.MODE_PRIVATE)  // creates file
            txtRead.text = sharedPref.getString(
                "result",
                "Nothing found"
            )       // use shared preference to read file or return default
        }

        btnGetProduct.setOnClickListener {
            val productId = edProductId.text.toString()
            val url = "$siteAddress/ecom/get_one.php?table=products&id=$productId"
            val requestQ = Volley.newRequestQueue(this)

            // Json Object request with 5 params method, url, jsonrequest, success action and error action
            val jobRequest = JsonObjectRequest(Request.Method.GET, url, null,
                Response.Listener { response ->
                    val productImage: String = response.getString("photo_url")
                    // use picasso to place the image
                    Picasso.with(this).load(productImage.replace("http://localhost/mcornel.com", siteAddress)).into(ivProductImage)
                    txtProductName.text = response.getString("name")
                    txtProductDescription.text = response.getString("description")
                    txtProductPrice.text = response.getString("price")
                },
                Response.ErrorListener { error ->
                    txtProductDescription.text = error.message
                })
            requestQ.add(jobRequest)
        }

        btnGetAll.setOnClickListener {
            val table = txtTable.text.toString()
            val intent = Intent(this, AllItemsAct::class.java)
            intent.putExtra("table", table)
            startActivity(intent)
        }

        btnAllProducts.setOnClickListener {
            val intent = Intent(this, ProductActivity::class.java)
            startActivity(intent)
        }


    }
}
