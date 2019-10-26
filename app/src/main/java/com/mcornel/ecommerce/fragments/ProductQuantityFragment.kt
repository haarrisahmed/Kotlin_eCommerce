package com.mcornel.ecommerce.fragments


import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.mcornel.ecommerce.R
import com.mcornel.ecommerce.activities.CartActivity
import com.mcornel.ecommerce.getSiteUrl
import com.mcornel.ecommerce.models.OrderInfo
import com.mcornel.ecommerce.models.UserInfo

/**
 * A simple [Fragment] subclass.
 */
class ProductQuantityFragment :
    android.app.DialogFragment() {  // todo check for what to use in place of depricated

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view = inflater.inflate(R.layout.fragment_product_quantity, container, false)
        val quantityInput = view.findViewById<EditText>(R.id.tiQuantity)
        val saveButton = view.findViewById<Button>(R.id.btnSaveQuantity)
        val siteUrl = getSiteUrl()

        saveButton.setOnClickListener {
            val url = "$siteUrl/ecom/add_to_cart.php"

            val quantity = quantityInput.text.toString()
            val productId = OrderInfo.productId.toString()
            val userId = UserInfo.id.toString()

            val requestQ = Volley.newRequestQueue(view.context)
            val addToCartRequest = object : StringRequest(
                Method.POST, url,
                Response.Listener { res ->
//                    if (res == "1") {
                    if (true) {
                        Toast.makeText(view.context, res, Toast.LENGTH_LONG).show()
//                        todo figure out how to dismiss the fragment. Meanwhile go to another activity
                        startActivity(Intent(view.context, CartActivity::class.java))
                    } else {
                        Toast.makeText(view.context, "Not Added", Toast.LENGTH_LONG).show()
                    }
                },
                Response.ErrorListener { err ->
                    Toast.makeText(view.context, "Error: ${err.message}", Toast.LENGTH_LONG).show()
                }
            ) {
                override fun getParams(): MutableMap<String, String> {
                    val parcel = HashMap<String, String>()
                    parcel["quantity"] = quantity
                    parcel["product_id"] = productId
                    parcel["user_id"] = userId
                    return parcel
                }
            }
            requestQ.add(addToCartRequest)

        }
        return view
    }


}
