package com.mcornel.ecommerce

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_product.*

class ProductActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product)


        val siteUrl = getSiteUrl()
        val url = "$siteUrl/ecom/get_all.php?table=products"

        val requestQ = Volley.newRequestQueue(this)

        val jarRequest = JsonArrayRequest(Request.Method.GET, url, null,
            Response.Listener { response ->
                val products = ArrayList<Product>()
                for (x in 0 until response.length()){
                    val jsonObject = response.getJSONObject(x)
                    val name = jsonObject.getString("name")
                    val desc = jsonObject.getString("description")
                    val price = jsonObject.getDouble("price")
                    val photoUrl = jsonObject.getString("photo_url")

                    products.add(Product(name = name, description = desc, price = price, photoUrl = photoUrl))
                }
                val adapter = ProductAdapter(this, products)
                rvProducts.layoutManager = LinearLayoutManager(this)
                rvProducts.adapter = adapter
            },
            Response.ErrorListener { error ->
                Toast.makeText(this, error.message, Toast.LENGTH_LONG).show()
            })

        requestQ.add(jarRequest)




    }
}
