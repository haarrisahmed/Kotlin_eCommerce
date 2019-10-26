package com.mcornel.ecommerce.models

import org.json.JSONObject

class UserInfo {
    companion object {
        var mobile:String=""
        var id:Int=0
        var json: JSONObject? =null
    }
}

class OrderInfo {
    companion object {
        var productId:Int=0
    }
}
