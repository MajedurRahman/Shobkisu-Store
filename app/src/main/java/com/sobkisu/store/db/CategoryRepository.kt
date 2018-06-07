package com.sobkisu.store.db

import android.util.Log
import com.sobkisu.store.SobKisuApplication
import com.sobkisu.store.model.Product
import com.sobkisu.store.model.ProductCategory
import io.realm.Realm

open class CategoryRepository() {
    val realm: Realm = Realm.getInstance(SobKisuApplication().getTestConfigaration())

    // Get All product Category as List
    fun getAllProductCategory() = realm.where(ProductCategory::class.java).findAllAsync()!!

    //Total number of product in given item category
    fun getAllProductCount(item: String) = realm.where(Product::class.java)
            .equalTo("productCategory", item).findAll()!!.size

    //Delete Product Category with corresponding Product item
    fun deleteProductCategoryByName(itemName: String): Boolean {
        try {
            realm.beginTransaction()
            realm.where(Product::class.java).equalTo("productCategory", itemName).findAll().deleteAllFromRealm()
            realm.where(ProductCategory::class.java).equalTo("pcName", itemName).findFirst()!!.deleteFromRealm()
            realm.commitTransaction()
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return true
    }

    //save Category Product
    fun saveProductCategory(productName: String) {
        try {
            realm.beginTransaction()
            var totalsize = realm.where(ProductCategory::class.java).findAll().size
            var category: ProductCategory = ProductCategory(totalsize.toLong() + 1, productName)
            realm.copyToRealm(category)
            realm.where(ProductCategory::class.java).findAll().forEach {
                Log.e("Category : ", it.pcName.toString())
            }
            realm.commitTransaction()
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }

}