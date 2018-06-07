package com.sobkisu.store.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import com.sobkisu.store.R
import com.sobkisu.store.db.CategoryRepository
import com.sobkisu.store.model.ProductCategory

class ProductCategoryAdapter(mainContext: Context, listOfCategory: ArrayList<ProductCategory>) : RecyclerView.Adapter<ProductCategoryAdapter.CategoryViewHolder>() {
    private var layoutInflater: LayoutInflater? = null
    var context: Context? = null
    var data: ArrayList<ProductCategory>? = null

    init {

        layoutInflater = LayoutInflater.from(mainContext)
        context = mainContext
        data = listOfCategory
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        return CategoryViewHolder(layoutInflater?.inflate(R.layout.item_category, parent, false), context!!)
    }

    override fun getItemCount(): Int {

        return data?.size!!
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        holder.categoryText!!.text = data!![position].pcName
        holder.countProduct!!.text = "Total Product : ".plus(CategoryRepository().getAllProductCount(data!![position].pcName!!))
        if (holder.onDeleteButtonClick(position, data)) {
            data!!.removeAt(position)
            notifyDataSetChanged()
        }

    }

    class CategoryViewHolder(itemView: View?, contextt: Context) : RecyclerView.ViewHolder(itemView) {
        var categoryText: TextView? = null
        var countProduct: TextView? = null
        var deleteButton: ImageButton? = null
        var context: Context? = null;

        init {
            context = contextt
            categoryText = itemView?.findViewById<TextView>(R.id.category_name_id)
            countProduct = itemView?.findViewById<TextView>(R.id.total_product_under_category_id)
            deleteButton = itemView?.findViewById<ImageButton>(R.id.categoryDelete)
        }

        fun onDeleteButtonClick(position: Int, item: ArrayList<ProductCategory>?): Boolean {
            var flag: Boolean = false
            deleteButton!!.setOnClickListener {
                flag = CategoryRepository().deleteProductCategoryByName(itemName = item!![position].pcName!!)
            }
            return flag

        }
    }


}