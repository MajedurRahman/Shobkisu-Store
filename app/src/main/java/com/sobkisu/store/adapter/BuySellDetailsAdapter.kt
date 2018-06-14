package com.sobkisu.store.adapter

import android.content.Context
import android.graphics.Color
import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.sobkisu.store.R
import com.sobkisu.store.db.ProductRepository
import com.sobkisu.store.model.BuySellInfo

class BuySellDetailsAdapter(val context: Context, var list: ArrayList<BuySellInfo>) : RecyclerView.Adapter<BuySellDetailsAdapter.MyviewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyviewHolder {
        return MyviewHolder(LayoutInflater.from(context).inflate(R.layout.buy_sell_details, parent, false))
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: MyviewHolder, position: Int) {
        holder.textViewName.text = getProductName(list[position].productId!!).productName
        holder.textViewPric.text = list[position].productPrice.toString() + " BDT "
        holder.textViewBuySellCount.text = " X   " + list[position].productCount.toString() + " Pcs"
        if (list[position].buySellType == 1) {
            holder.pCard.setCardBackgroundColor(Color.parseColor("#E9D460"))

        } else {
            holder.pCard.setCardBackgroundColor(Color.parseColor("#00e661"))

        }

    }

    fun filter(searchData: ArrayList<BuySellInfo>) {
        this.list = searchData
        notifyDataSetChanged()
    }

    fun getProductName(item: Long) = ProductRepository().getProductByIdAll(item)

    class MyviewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {
        var textViewName = itemView!!.findViewById<TextView>(R.id.productName)
        var textViewPric = itemView!!.findViewById<TextView>(R.id.productPrice)
        var textViewBuySellCount = itemView!!.findViewById<TextView>(R.id.productCount)
        var pCard = itemView!!.findViewById<CardView>(R.id.productCard)
    }
}