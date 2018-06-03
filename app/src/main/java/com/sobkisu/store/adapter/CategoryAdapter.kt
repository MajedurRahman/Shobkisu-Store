package com.sobkisu.store.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.sobkisu.store.R
import com.sobkisu.store.model.Category

class CategoryAdapter(context: Context, private val listOfCategory: ArrayList<Category>) : RecyclerView.Adapter<CategoryAdapter.CategoryAdapterVegHolder>() {
    private var layoutInflater: LayoutInflater? = null

    init {
        layoutInflater = LayoutInflater.from(context)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryAdapterVegHolder {
        return CategoryAdapterVegHolder(layoutInflater!!.inflate(R.layout.item_inside_catagory, parent, false))
    }

    override fun getItemCount(): Int {
        return listOfCategory.size
    }

    override fun onBindViewHolder(holder: CategoryAdapterVegHolder, position: Int) {
        holder.imageCategory!!.setBackgroundResource(listOfCategory[position].imageId)
        holder.categoryText!!.text = listOfCategory[position].categoryName
    }

    class CategoryAdapterVegHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {
        var imageCategory: ImageView? = null
        var categoryText: TextView? = null

        init {
            imageCategory = itemView?.findViewById<ImageView>(R.id.id_item_image)
            categoryText = itemView?.findViewById<TextView>(R.id.id_item_text)
        }
    }

}

