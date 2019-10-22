package com.mcornel.ecommerce

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_home.*
import org.json.JSONObject

class HomeAct : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val siteUrl = getSiteUrl()
        val url = "$siteUrl/ecom/get_categories.php"
        var category_names = ArrayList<String>()
        var categories = ArrayList<JSONObject>()


        var requestQ = Volley.newRequestQueue(this)
        val jarCategories = JsonArrayRequest(Request.Method.GET, url, null,
            Response.Listener { response ->
                for (x in 0 until response.length()) {
                    var jsonObject = response.getJSONObject(x)
                    category_names.add(jsonObject.getString("name"))
                    categories.add(jsonObject)
                }

//                passing the array to the list view
                var arrayAdapter = ArrayAdapter(this, R.layout.category_row, category_names)
                lvCategories.adapter = arrayAdapter

            },
            Response.ErrorListener { error ->
                Toast.makeText(this, error.message, Toast.LENGTH_LONG).show()
            })

        requestQ.add(jarCategories)

        lvCategories.setOnItemClickListener { parent, view, position, id ->
            val category = categories[position]
            val products = category.getJSONArray("products")

            val productsArrayList = ArrayList<JSONObject>()
            for (i in 0 until products.length()) {
                productsArrayList.add(products.getJSONObject(i))
            }

            val intent = Intent(this, CategoryProductsActivity::class.java)
            intent.putExtra("category", category.toString())
            Toast.makeText(this, categories[position].getString("name"), Toast.LENGTH_LONG).show()
            startActivity(intent)
        }
    }
}
