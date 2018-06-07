package com.sobkisu.store.activity

import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.widget.EditText
import com.sobkisu.store.R
import com.sobkisu.store.adapter.ProductCategoryAdapterJava
import com.sobkisu.store.db.CategoryRepository
import com.sobkisu.store.model.ProductCategory
import kotlinx.android.synthetic.main.activity_add_category.*
import kotlinx.android.synthetic.main.add_category_layout.*

class AddCategoryActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_category)

        initActions()
        initView()

    }

    private fun initView() {
        recyclerViewProductCategory.layoutManager = LinearLayoutManager(this)

        var data = ArrayList<ProductCategory>()
        CategoryRepository().getAllProductCategory().forEach {
            data.add(it)
        }
        recyclerViewProductCategory.adapter = ProductCategoryAdapterJava(this, data)
    }

    fun initActions() {
        try {
            id_add_category.setOnClickListener {
                var dialog: Dialog = Dialog(this)
                dialog.setContentView(R.layout.add_category_layout)
                dialog.show()

                dialog.createDialogSave.setOnClickListener {
                    var nameText = dialog.findViewById<EditText>(R.id.editTextCategoryDialog)
                    var categoryName = nameText.text.toString().trim()

                    if (!categoryName.contentEquals("")) {
                        CategoryRepository().saveProductCategory(categoryName)
                        startActivity(Intent(this, AddCategoryActivity::class.java))
                        finish()
                        dialog.dismiss()
                    } else {
                        nameText.error = "Name is empty!!"
                    }
                }
            }

        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

}