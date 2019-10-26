package com.mcornel.ecommerce.adapters

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.mcornel.ecommerce.*
import com.mcornel.ecommerce.fragments.ProductQuantityFragment
import com.mcornel.ecommerce.models.OrderInfo
import com.mcornel.ecommerce.models.Product
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.product_row.view.*

//Consider this as a controller in web

class ProductAdapter(var context: Context, var productList: ArrayList<Product>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {


    class ProductHolder(view: View) : RecyclerView.ViewHolder(view) {
        var tvProductName = view.findViewById<TextView>(R.id.tvProductName)
        var tvProductDesc = view.findViewById<TextView>(R.id.tvProductDescription)
        var tvProductPrice = view.findViewById<TextView>(R.id.tvProductPrize)
        var ivPoductImage = view.findViewById<ImageView>(R.id.ivProductImage)
        val siteAddress = getSiteUrl()

        fun bindData(product: Product? = null) {
            val photoLink =
                product!!.photoUrl.replace(" ", "%20")        // remove spaces from file names
            tvProductName.text = product.name
            tvProductDesc.text = product.description
            tvProductPrice.text = product.price.toString()
            Picasso.with(itemView.context)
                .load(photoLink.replace("http://localhost/mcornel.com", siteAddress))
                .into(ivPoductImage)

            itemView.btnAddToCart.setOnClickListener {
                val productId = product.id
                OrderInfo.productId = productId
                var fragment = ProductQuantityFragment()
                var fragmentManager = (itemView.context as Activity).fragmentManager
                fragment.show(fragmentManager, "Quantity")

            }
        }
    }

    override fun getItemCount(): Int {
        return productList.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {     // bind data into the holder
        (holder as ProductHolder).bindData(product = productList[position])
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {    // connects the holder and the  layout
        return ProductHolder(
            LayoutInflater.from(context).inflate(
                R.layout.product_row,
                parent,
                false
            )
        )
    }

}