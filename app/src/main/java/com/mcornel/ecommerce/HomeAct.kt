package com.mcornel.ecommerce

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Html
import android.text.method.ScrollingMovementMethod
import android.widget.ArrayAdapter
import android.widget.Toast
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
        var categories = ArrayList<String>()

        var requestQ = Volley.newRequestQueue(this)
        val jarCategories = JsonArrayRequest(Request.Method.GET, url, null,
            Response.Listener { response ->
                for (x in 0 until response.length()) {
                    var jsonObject = response.getJSONObject(x)
                    categories.add(jsonObject.getString("name"))
                }

//                passing the array to the list view
                var arrayAdapter = ArrayAdapter(this, R.layout.category_row, categories)
                lvCategories.adapter = arrayAdapter

            },
            Response.ErrorListener { error ->
                Toast.makeText(this, error.message, Toast.LENGTH_LONG).show()
            })

        requestQ.add(jarCategories)
    }
}
