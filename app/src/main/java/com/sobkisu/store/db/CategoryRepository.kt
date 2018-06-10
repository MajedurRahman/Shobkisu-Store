package com.sobkisu.store.db

import com.sobkisu.store.SobKisuApplication
import com.sobkisu.store.model.Product
import com.sobkisu.store.model.ProductCategory
import com.sobkisu.store.model.active
import com.sobkisu.store.model.deleted
import io.realm.Realm

open class CategoryRepository() {
    val realm: Realm = Realm.getInstance(SobKisuApplication().getTestConfigaration())


    // Get All product Category as List
    fun getAllProductCategory() = realm.where(ProductCategory::class.java).equalTo("status", active).findAll()!!

    //Total number of product in given item category
    fun getAllProductCount(item: String) = realm.where(Product::class.java)
            .equalTo("productCategory", item)
            .and()
            .equalTo("status", active).findAll()!!.size

    //Delete Product Category with corresponding Product item
    fun deleteProductCategoryByName(itemName: String): Boolean {
        try {
            realm.beginTransaction()
            realm.where(Product::class.java).equalTo("productCategory", itemName).equalTo("status", active).findAll().forEach {
                it.status = deleted
            }
            realm.where(ProductCategory::class.java).equalTo("pcName", itemName).findAll().forEach {
                it.status = deleted
            }
            realm.commitTransaction()
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return true
    }

    //save Category Product
    fun saveProductCategory(productName: String) {

        realm.executeTransaction {
            var totalsize = realm.where(ProductCategory::class.java).findAll().size
            var category: ProductCategory = ProductCategory(totalsize.toLong() + 1, productName, createdAt = System.currentTimeMillis(), updatedAt = System.currentTimeMillis(), status = active)
            realm.copyToRealm(category)
        }
    }

}