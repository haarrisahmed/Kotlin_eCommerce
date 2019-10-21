package com.mcornel.ecommerce

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Html
import android.text.method.ScrollingMovementMethod
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_all_items.*
import org.json.JSONObject

class AllItemsAct : AppCompatActivity() {

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_all_items)
        val table = intent.getStringExtra("table")
        val siteUrl = getSiteUrl()
        val url = "$siteUrl/ecom/get_all.php?table=$table"
        var resultText = ""

        tvTableName.text = table?.toUpperCase()

        val requestQ = Volley.newRequestQueue(this)

        val jarRequest = JsonArrayRequest(Request.Method.GET, url, null,
            Response.Listener { response ->
                for (x in 0 until response.length()) {
                    var jsonObject = response.getJSONObject(x)

                    var objectString = "<p>"
//                    Get values dynamically
                    for(key in jsonObject.keys()) {
                        objectString += "<b>$key:</b>  ${jsonObject[key]} <br>"
                    }
                    resultText += "$objectString<br></p>"
                }

//                Utilize html rendering abd make scrollable
                tvResultList.text = Html.fromHtml(resultText)
                tvResultList.movementMethod = ScrollingMovementMethod()
            },
            Response.ErrorListener { error ->
                tvResultList.text = error.message
            })

        requestQ.add(jarRequest)

    }

    private fun jsonToMap(json: JSONObject): Map<String, String> {
        val map: MutableMap<String, String> = linkedMapOf()
        for (key in json.keys()) {
            map.put(key, json[key] as String)
        }
        return map
    }
}
