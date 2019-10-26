package com.mcornel.ecommerce.models

import org.json.JSONObject

class CartItem(
    val id:Int=0,
    val photo_url: String = "",
    val category_name: String = "",
    val product_name: String = "",
    val quantity:Int = 0,
    val price:Double = 0.0,
    val cost:Double = 0.0,
    val json: JSONObject = JSONObject()
) {

}