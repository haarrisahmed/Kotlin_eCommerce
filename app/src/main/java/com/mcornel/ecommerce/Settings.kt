package com.mcornel.ecommerce

fun getSiteUrl(): String {

//    val env = "local"
        var env = "live"
    var siteAddress = "https://mcornel.com"
    if (env == "local") {
        siteAddress = "http://192.168.100.8/mcornel.com"
    }
    return siteAddress
}