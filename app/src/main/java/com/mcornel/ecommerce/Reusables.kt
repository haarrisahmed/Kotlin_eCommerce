package com.mcornel.ecommerce

fun getSiteUrl(): String {

    val env = "local"
//    val env = "live"
    var siteAddress = "https://mcornel.com"
    if (env == "local") {
        siteAddress = "http://${setLocalIp()}/mcornel.com"
    }
    return siteAddress
}

fun setLocalIp(): String {
    return "192.168.43.4"
//    return "192.168.100.8"
}