package com.sobkisu.store.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
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
        recyclerViewProductManage.layoutManager = GridLayoutManager(this, 2) as RecyclerView.LayoutManager?

    }

    private fun getManageCategoryList(): ArrayList<Category> {
        val categoryList = ArrayList<Category>()
        categoryList.add(Category("Add Product", R.drawable.ic_add_cart))
        categoryList.add(Category("Manage Category", R.drawable.ic_category_checklist))
        categoryList.add(Category("Show Product", R.drawable.ic_add_product))
        return categoryList
    }

}
