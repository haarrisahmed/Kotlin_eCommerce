package com.mcornel.ecommerce

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_category_products.*
import org.json.JSONObject


class CategoryProductsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_category_products)

        val jsonCategory = JSONObject(intent.getStringExtra("category")!!)

        tvCategoryName.text = jsonCategory.getString("name")
        val jarProducts = jsonCategory.getJSONArray("products")
        val products = ArrayList<Product>()
        for (x in 0 until jarProducts.length()){
            val jsonObject = jarProducts.getJSONObject(x)
            val name = jsonObject.getString("name")
            val desc = jsonObject.getString("description")
            val price = jsonObject.getDouble("price")
            val photoUrl = jsonObject.getString("photo_url")

            products.add(Product(name = name, description = desc, price = price, photoUrl = photoUrl))
        }
        val adapter = ProductAdapter(this, products)
        rvProducts.layoutManager = LinearLayoutManager(this)
        rvProducts.adapter = adapter
    }
}
