package com.sobkisu.store.adapter

import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import com.sobkisu.store.R
import com.sobkisu.store.activity.*
import com.sobkisu.store.model.Activity
import com.sobkisu.store.model.Category


open class CategoryAdapter(val context: Context, private val listOfCategory: ArrayList<Category>, current: Activity) : RecyclerView.Adapter<CategoryAdapter.CategoryAdapterVegHolder>() {
    private var layoutInflater: LayoutInflater? = null
    private var currentActivity: Activity? = null
    var contextMain: Context? = null

    init {
        layoutInflater = LayoutInflater.from(context)
        contextMain = context
        currentActivity = current
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryAdapterVegHolder {
        return CategoryAdapterVegHolder(layoutInflater!!.inflate(R.layout.item_inside_catagory, parent, false))
    }

    override fun getItemCount(): Int {
        return listOfCategory.size
    }

    override fun onBindViewHolder(holder: CategoryAdapterVegHolder, position: Int) {
        try {
            holder.imageCategory!!.setBackgroundResource(listOfCategory[position].imageId)
            holder.categoryText!!.text = listOfCategory[position].categoryName
            holder.onItemClick(position, currentActivity, contextMain)
        } catch (e: Exception) {
            e.printStackTrace()

        }
    }

    class CategoryAdapterVegHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {
        var imageCategory: ImageView? = null
        var categoryText: TextView? = null
        private var categoryCard: RelativeLayout? = null

        init {
            imageCategory = itemView?.findViewById<ImageView>(R.id.id_item_image)
            categoryText = itemView?.findViewById<TextView>(R.id.id_item_text)
            categoryCard = itemView?.findViewById<RelativeLayout>(R.id.id_item_itemCard)
        }

        fun onItemClick(position: Int, currentActivity: Activity?, contextMain: Context?) {
            try {
                categoryCard?.setOnClickListener {
                    if (currentActivity == Activity.ManageProduct) {
                        if (position == 1) {
                            contextMain?.startActivity(Intent(contextMain, AddProductActivity::class.java))
                        } else if (position == 2) {
                            contextMain?.startActivity(Intent(contextMain, AddCategoryActivity::class.java))
                        } else if (position == 0) {
                            contextMain?.startActivity(Intent(contextMain, ShowProductActivity::class.java))
                        } else if (position == 3) {
                            contextMain?.startActivity(Intent(contextMain, BuyActivity::class.java))
                        } else if (position == 4) {
                            contextMain?.startActivity(Intent(contextMain, SellActivity::class.java))
                        } else if (position == 0) {
                            contextMain?.startActivity(Intent(contextMain, ShowProductActivity::class.java))
                        }
                    } else if (currentActivity == Activity.CashDetails) {
                        if (position == 1) {
                            contextMain!!.startActivity(Intent(contextMain, CashInOutActivity::class.java).putExtra("from", Activity.Deposit))
                        } else if (position == 2) {
                            contextMain!!.startActivity(Intent(contextMain, CashInOutActivity::class.java).putExtra("from", Activity.CashOut))
                        }

                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

    }

}

