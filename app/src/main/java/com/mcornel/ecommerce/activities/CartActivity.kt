package com.mcornel.ecommerce.activities

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley.newRequestQueue
import com.mcornel.ecommerce.R
import com.mcornel.ecommerce.adapters.CartAdapter
import com.mcornel.ecommerce.getSiteUrl
import com.mcornel.ecommerce.models.CartItem
import com.mcornel.ecommerce.models.UserInfo
import kotlinx.android.synthetic.main.activity_cart.*

class CartActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cart)

        val url = "${getSiteUrl()}/ecom/get_cart_items.php?user_id=${UserInfo.id}"
        val requestQ = newRequestQueue(this)

        val CartJar = JsonArrayRequest(
            Request.Method.GET, url, null,
            Response.Listener { res ->
                val cartItems = ArrayList<CartItem>()
                var cartTotal:Double = 0.0
                for (i in 0 until res.length()) {
                    val jsonObject = res.getJSONObject(i)
                    cartTotal += jsonObject.getDouble("cost")
                    cartItems.add(
                        CartItem(
                            id = jsonObject.getInt("id"),
                            photo_url = jsonObject.getString("photo_url"),
                            category_name = jsonObject.getString("category"),
                            product_name = jsonObject.getString("product"),
                            quantity = jsonObject.getInt("quantity"),
                            price = jsonObject.getDouble("price"),
                            cost = jsonObject.getDouble("cost"),
                            json = jsonObject
                        )
                    )
                }

                rvCartItems.layoutManager = LinearLayoutManager(this)
                rvCartItems.adapter = CartAdapter(this, cartItems)
                val cartTitle = "${tvCartTitle.text} $cartTotal"
                tvCartTitle.text = cartTitle
            },
            Response.ErrorListener { err ->
                Toast.makeText(this, err.message, Toast.LENGTH_LONG).show()
            })

        requestQ.add(CartJar)
    }
}
