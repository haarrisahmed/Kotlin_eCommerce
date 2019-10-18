package com.mcornel.ecommerce

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_all_items.*

class AllItemsAct : AppCompatActivity() {

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_all_items)
        val table = intent.getStringExtra("table")
        val siteUrl = getSiteUrl()
        val url = "$siteUrl/ecom/get_all.php?table=$table"
        var resultText = ""

        val requestQ = Volley.newRequestQueue(this)

        val jarRequest = JsonArrayRequest(Request.Method.GET, url, null,
            Response.Listener { response ->
                for (x in 0 until response.length())
//                    todo figure out how to get dynamic attributes
                    resultText +=  response.getJSONObject(x).getString("name") + "\n"

                tvResultList.text = resultText
            },
            Response.ErrorListener { error ->
                tvResultList.text = error.message
            })

        requestQ.add(jarRequest)

    }
}
