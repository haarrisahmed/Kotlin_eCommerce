package com.mcornel.ecommerce.activities

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley
import com.mcornel.ecommerce.models.Product
import com.mcornel.ecommerce.R
import com.mcornel.ecommerce.adapters.ProductAdapter
import com.mcornel.ecommerce.getSiteUrl
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
                for (x in 0 until response.length()) {
                    val jsonObject = response.getJSONObject(x)

                    products.add(
                        Product(
                            name = jsonObject.getString("name"),
                            description = jsonObject.getString("description"),
                            price = jsonObject.getDouble("price"),
                            photoUrl = jsonObject.getString("photo_url"),
                            id = jsonObject.getInt("id")
                        )
                    )
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
