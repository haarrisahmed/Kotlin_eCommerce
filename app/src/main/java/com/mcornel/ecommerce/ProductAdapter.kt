package com.mcornel.ecommerce

import android.content.Context
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.view.menu.ActionMenuItemView
import androidx.core.net.toUri
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

//Consider this as a controller in web

class ProductAdapter(var context:Context, var productList:ArrayList<Product>):RecyclerView.Adapter<RecyclerView.ViewHolder>() {


    class ProductHolder(itemView:View):RecyclerView.ViewHolder(itemView) {
        var tvProductName = itemView.findViewById<TextView>(R.id.tvProductName)
        var tvProductDesc = itemView.findViewById<TextView>(R.id.tvProductDescription)
        var tvProductPrice = itemView.findViewById<TextView>(R.id.tvProductPrize)
        var ivPoductImage = itemView.findViewById<ImageView>(R.id.ivProductImage)
        val siteAddress = getSiteUrl()

        fun bindData(name:String, desc:String, price:Double, photoUrl:String) {
            tvProductName.text = name
            tvProductDesc.text = desc
            tvProductPrice.text = price.toString()
            Picasso.with(itemView.context).load(photoUrl.replace("http://localhost/mcornel.com", siteAddress)).into(ivPoductImage)
        }
    }

    override fun getItemCount(): Int {
        return productList.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
//        bind data into the holder
        var currentProduct = productList[position]
        (holder as ProductHolder).bindData(currentProduct.name, currentProduct.description, currentProduct.price, currentProduct.photoUrl)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
//        connects the holder and the  layout
        var view = LayoutInflater.from(context).inflate(R.layout.product_row, parent, false)
        return ProductHolder(view)
    }

}