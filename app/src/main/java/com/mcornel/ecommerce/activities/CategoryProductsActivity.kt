package com.mcornel.ecommerce.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.mcornel.ecommerce.models.Product
import com.mcornel.ecommerce.R
import com.mcornel.ecommerce.adapters.ProductAdapter
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
        for (x in 0 until jarProducts.length()) {
            val jsonObject = jarProducts.getJSONObject(x)

            products.add(
                Product(
                    id = jsonObject.getInt("id"),
                    name = jsonObject.getString("name"),
                    description = jsonObject.getString("description"),
                    price = jsonObject.getDouble("price"),
                    photoUrl = jsonObject.getString("photo_url")
                )
            )
        }
        val adapter = ProductAdapter(this, products)
        rvProducts.layoutManager = LinearLayoutManager(this)
        rvProducts.adapter = adapter
    }
}
