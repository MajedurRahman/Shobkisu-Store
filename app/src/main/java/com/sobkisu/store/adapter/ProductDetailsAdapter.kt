package com.sobkisu.store.adapter

import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import android.widget.TextView
import com.sobkisu.store.R
import com.sobkisu.store.activity.ProductDetailsActivity
import com.sobkisu.store.db.BuySellRepository
import com.sobkisu.store.db.ProductRepository
import com.sobkisu.store.model.Product

class ProductDetailsAdapter(context: Context, dataList: ArrayList<Product>) : RecyclerView.Adapter<ProductDetailsAdapter.ProductViewHolder>() {
    private var mainContext: Context? = null
    private var layoutinflater: LayoutInflater? = null
    private var productList: ArrayList<Product>? = null


    init {
        mainContext = context
        layoutinflater = LayoutInflater.from(context)
        productList = dataList
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val view: View = layoutinflater?.inflate(R.layout.item_product_catagory, parent, false)!!
        return ProductViewHolder(view)
    }

    override fun getItemCount(): Int {

        return productList!!.size
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {

        try {
            val transactionData = BuySellRepository().getTransacByProductId(productList!![position].Id!!)
            holder.productName.text = productList!![position].productName.toString()
            holder.productAvailable.text = "Available: ${transactionData.productAvailable} pcs "
            holder.productSold.text = "Sold : ${transactionData.productSold} pcs"
            holder.productPrice.text = "Price : " + productList!![position].productPrice.toString() + " BDT"

            onCardClick(holder, position)
        } catch (e: Exception) {

            e.printStackTrace()
        }

    }

    private fun getAvailableProduct(id: Long?) = BuySellRepository().getAvailableProduct(id!!)


    private fun onCardClick(holder: ProductViewHolder, position: Int) {
        holder.productCard.setOnClickListener {
            mainContext!!.startActivity(Intent(mainContext, ProductDetailsActivity::class.java).putExtra("productId", productList!![position].Id))
        }


        holder.productCard.setOnLongClickListener {
            ProductRepository().deleteProductById(productList?.get(position)!!.Id!!)
            productList!!.removeAt(position)
            notifyDataSetChanged()
            it.isClickable
        }
    }

    open class ProductViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {
        var productName: TextView = itemView!!.findViewById<TextView>(R.id.id_item_product_name_text)
        var productPrice: TextView = itemView!!.findViewById<TextView>(R.id.productPrice)
        var productAvailable: TextView = itemView!!.findViewById<TextView>(R.id.productAvailable)
        var productSold: TextView = itemView!!.findViewById<TextView>(R.id.productSold)
        var productCard: RelativeLayout = itemView!!.findViewById<RelativeLayout>(R.id.id_item_itemCard)
    }
}