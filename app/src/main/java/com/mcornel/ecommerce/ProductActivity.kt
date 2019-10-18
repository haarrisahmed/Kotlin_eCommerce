package com.mcornel.ecommerce

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_product.*

class ProductActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product)

        val products = ArrayList<Product>()

        val siteUrl = getSiteUrl()
        val url = "$siteUrl/ecom/get_all.php?table=products"
        products.add(Product(name = "Martin", description = "A description", price = 44.6, photoUrl = "$siteUrl/ecom/img/breaking_bad.jpg"))
        products.add(Product(name = "Martin", description = "A description", price = 44.6, photoUrl = "$siteUrl/ecom/img/breaking_bad.jpg"))
        products.add(Product(name = "Martin", description = "A description", price = 44.6, photoUrl = "$siteUrl/ecom/img/breaking_bad.jpg"))

        val adapter = ProductAdapter(this, products)
        rvProducts.layoutManager = LinearLayoutManager(this)
        rvProducts.adapter = adapter

    }
}
