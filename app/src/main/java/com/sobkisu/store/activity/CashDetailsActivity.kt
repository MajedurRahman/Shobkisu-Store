package com.sobkisu.store.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import com.sobkisu.store.R
import com.sobkisu.store.adapter.CategoryAdapter
import com.sobkisu.store.model.Activity
import com.sobkisu.store.model.Category
import kotlinx.android.synthetic.main.activity_cash_details.*

class CashDetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cash_details)

        initLayout()
    }

    private fun initLayout() {
        setUpApplicationMenu()
    }

    private fun setUpApplicationMenu() {

        recyclerViewCashDetails.adapter = CategoryAdapter(this, getManageCategoryList(), Activity.CashDetails)
        recyclerViewCashDetails.setHasFixedSize(true)
        recyclerViewCashDetails.layoutManager = GridLayoutManager(this, 2)

    }

    private fun getManageCategoryList(): ArrayList<Category> {
        val categoryList = ArrayList<Category>()
        categoryList.add(Category("Cash Details", R.drawable.ic_money_color))
        categoryList.add(Category("Deposit", R.drawable.ic_cash))
        categoryList.add(Category("Cash Out", R.drawable.ic_credit_card))

        return categoryList
    }

}
