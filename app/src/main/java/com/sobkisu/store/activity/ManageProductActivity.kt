package com.sobkisu.store.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import com.sobkisu.store.R
import com.sobkisu.store.adapter.CategoryAdapter
import com.sobkisu.store.model.Activity
import com.sobkisu.store.model.Category
import kotlinx.android.synthetic.main.activity_manage_product.*

class ManageProductActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_manage_product)
        initLayout()
    }

    private fun initLayout() {
        setUpApplicationMenu()
    }

    private fun setUpApplicationMenu() {

        recyclerViewProductManage.adapter = CategoryAdapter(this, getManageCategoryList(), Activity.ManageProduct)
        recyclerViewProductManage.setHasFixedSize(true)
        recyclerViewProductManage.layoutManager = GridLayoutManager(this, 2)!!

    }

    private fun getManageCategoryList(): ArrayList<Category> {
        val categoryList = ArrayList<Category>()
        categoryList.add(Category("Show Product", R.drawable.ic_discount))
        categoryList.add(Category("Add Product", R.drawable.ic_add_trolley))
        categoryList.add(Category("Manage Category", R.drawable.ic_category_checklist))
        categoryList.add(Category("Buy Product", R.drawable.ic_trolley))
        categoryList.add(Category("Sell Product", R.drawable.ic_shopping_cart2))

        return categoryList
    }

}
