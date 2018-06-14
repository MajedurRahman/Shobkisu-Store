package com.sobkisu.store.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import com.sobkisu.store.R
import com.sobkisu.store.model.Product
import com.sobkisu.store.model.Transaction
import com.sobkisu.store.utils.BuySellDialog

class BuyCellAdapter(val context: Context, var data: ArrayList<Product>, val from: Transaction) : RecyclerView.Adapter<BuyCellAdapter.BuyCellViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BuyCellViewHolder {
        return BuyCellViewHolder(LayoutInflater.from(context).inflate(R.layout.item_buy_sell, parent, false))
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: BuyCellViewHolder, position: Int) {

        if (from == Transaction.Buy) {
            holder.buyCellButton.text = "Buy"
        } else if (from == Transaction.Sell) {
            holder.buyCellButton.text = "Sell"
        }

        holder.nameProduct.text = data[position].productName!!

        onBuyClick(position, holder)
    }

    fun filter(searchData: ArrayList<Product>) {
        this.data = searchData
        notifyDataSetChanged()

    }
    private fun onBuyClick(position: Int, holder: BuyCellViewHolder) {
        if (from == Transaction.Sell) {
            holder.buyCellButton.setOnClickListener {
                BuySellDialog(context).sellDialog(data[position].Id)
            }
        } else if (from == Transaction.Buy) {
            holder.buyCellButton.setOnClickListener {
                BuySellDialog(context).buyDialog(data[position].Id)
            }
        }

    }

    class BuyCellViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {
        var nameProduct = itemView!!.findViewById<TextView>(R.id.productName)!!
        var availProduct = itemView!!.findViewById<TextView>(R.id.availableProduct)!!
        var buyCellButton = itemView!!.findViewById<Button>(R.id.buyCellButton)!!
    }
}