package com.mcornel.ecommerce.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.mcornel.ecommerce.R
import com.mcornel.ecommerce.getSiteUrl
import com.mcornel.ecommerce.models.CartItem
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.cart_item_row.view.*

class CartAdapter(var context: Context, var cartItems: ArrayList<CartItem>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ItemHolder(LayoutInflater.from(context).inflate(R.layout.cart_item_row, parent, false))
    }

    override fun getItemCount(): Int {
        return cartItems.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as ItemHolder).bindData(item = cartItems[position])
    }

    class ItemHolder(view: View) : RecyclerView.ViewHolder(view) {
        var tvProductName = view.findViewById<TextView>(R.id.tvProductName)
        var tvQuantity = view.findViewById<TextView>(R.id.tvQuantity)
        var tvPrice = view.findViewById<TextView>(R.id.tvPrice)
        var tvCost = view.findViewById<TextView>(R.id.tvCost)
        var ivProductImage = view.findViewById<ImageView>(R.id.ivProductImage)
        var btnRemoveFromCart = view.findViewById<Button>(R.id.btnRemoveFromCart)

        val siteAddress = getSiteUrl()

        fun bindData(item:CartItem? = null) {
            val photoLink:String = item!!.photo_url.replace(" ", "%20")
            tvProductName.text = "${item.category_name}: ${item.product_name}"
            tvQuantity.text = "${tvQuantity.text}: ${item.quantity}"
            tvPrice.text = "${tvPrice.text}: ${item.price}"
            tvCost.text = "${tvCost.text}: ${item.cost}"

            Picasso.with(itemView.context)
                .load(photoLink.replace("http://localhost/mcornel.com", siteAddress))
                .into(ivProductImage)

            btnRemoveFromCart.setOnClickListener {
//                todo remove from cart by reducing
                Toast.makeText(itemView.context, "TO implement remove from cart.", Toast.LENGTH_LONG).show()
            }
        }
    }
}
