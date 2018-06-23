package com.sobkisu.store.adapter

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.BitmapDrawable
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory
import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.sobkisu.store.R
import com.sobkisu.store.db.ProductRepository
import com.sobkisu.store.model.BuySellInfo
import com.sobkisu.store.utils.AppUtils


class BuySellDetailsAdapter(val context: Context, var list: ArrayList<BuySellInfo>) : RecyclerView.Adapter<BuySellDetailsAdapter.MyviewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyviewHolder {
        return MyviewHolder(LayoutInflater.from(context).inflate(R.layout.buy_sell_details, parent, false))
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: MyviewHolder, position: Int) {
        try {
            holder.textViewName.text = getProductName(list[position].productId!!).productName
            holder.textViewPrice.text = list[position].productPrice.toString() + " BDT "
            holder.textViewBuySellCount.text = " X   " + list[position].productCount.toString() + " Pcs"


            if (list[position].buySellType == 1) {
                holder.pCard.setCardBackgroundColor(Color.parseColor("#E9D460"))
                holder.buySellType.text = "Buy"
            } else {
                holder.pCard.setCardBackgroundColor(Color.parseColor("#00e661"))
                holder.buySellType.text = "Sell"

            }
            val productImage = ProductRepository().getProductImageByProductId(list[position].productId)

            if (productImage != null) {
                val image = BitmapDrawable(context.resources, AppUtils().convertStringToImage(productImage.imageString!!)!!)
                val roundedDrawable = RoundedBitmapDrawableFactory.create(context.resources, AppUtils().convertStringToImage(productImage.imageString!!))
                roundedDrawable.cornerRadius = 8.toFloat()
                holder.productImage.background = roundedDrawable

            } else {
                holder.productImage.background = context.resources.getDrawable(R.drawable.ic_intersect_circle)
            }

        } catch (e: Exception) {
            e.printStackTrace()
        }

    }

    fun filter(searchData: ArrayList<BuySellInfo>) {
        this.list = searchData
        notifyDataSetChanged()
    }

    private fun getProductName(item: Long) = ProductRepository().getProductByIdAll(item)

    class MyviewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {
        var textViewName = itemView!!.findViewById<TextView>(R.id.productName)!!
        var textViewPrice = itemView!!.findViewById<TextView>(R.id.productPrice)!!
        var textViewBuySellCount = itemView!!.findViewById<TextView>(R.id.productCount)!!
        var buySellType = itemView!!.findViewById<TextView>(R.id.buySellType)!!
        var pCard = itemView!!.findViewById<CardView>(R.id.productCard)!!
        var productImage = itemView!!.findViewById<ImageView>(R.id.productImage)!!
    }
}