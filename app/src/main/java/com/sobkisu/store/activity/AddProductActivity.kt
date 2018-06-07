package com.sobkisu.store.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.sobkisu.store.R
import com.sobkisu.store.db.CategoryRepository
import com.sobkisu.store.db.ProductRepository
import com.sobkisu.store.model.Product
import com.sobkisu.store.utils.log
import com.sobkisu.store.utils.snackBar
import kotlinx.android.synthetic.main.activity_add_product.*

class AddProductActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_product)


        initAddProductActivity()


    }

    private fun initActions(data: ArrayList<String>) {
        try {
            id_product_category.setItems(data)
            var categoryProduct: String = ""
            id_product_category.setOnItemSelectedListener { _, _, _, item ->
                categoryProduct = item as String
            }

            addProductButtonSave.setOnClickListener {
                saveProduct(categoryProduct, it)
            }

        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun saveProduct(categoryProduct: String, it: View) {
        try {
            val name = productNameId.text.toString()
            val price = productPriceId.text.toString()
            if (categoryProduct.contentEquals("") ||
                    categoryProduct.contentEquals("Select Category") ||
                    name.contentEquals("") ||
                    price.contentEquals("")) {
                snackBar("Product information missing!!", it)
            } else {
                val priceInt = price.toIntOrNull()
                log(priceInt.toString())
                if (ProductRepository().saveProduct(Product(productCategory = categoryProduct, productPrice = priceInt, productName = name))) {
                    snackBar("Product Saved Successfully!!", it)
                    finish()
                }
            }

        } catch (e: Exception) {
        }
    }

    private fun initAddProductActivity() {
        val data = ArrayList<String>()

        data.add("Select Category")
        CategoryRepository().getAllProductCategory().forEach {
            data.add(it.pcName!!)
        }

        initActions(data)

    }
}



