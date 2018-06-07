package com.sobkisu.store.db

import com.sobkisu.store.SobKisuApplication
import com.sobkisu.store.model.Product
import io.realm.Realm

class ProductRepository {
    val realm: Realm = Realm.getInstance(SobKisuApplication().getTestConfigaration())


    fun saveProduct(item: Product): Boolean {
        try {
            realm.beginTransaction()

            var max = realm.where(Product::class.java).findAll().size
            item.Id = max.toLong() + 1
            realm.copyToRealm(item)
            realm.commitTransaction()
            return true;
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return false
    }

    fun getAllProduct() = realm.where(Product::class.java).findAll()!!

    fun deleteProductById(id: Long) {
        realm.executeTransaction {
            it.where(Product::class.java).equalTo("id", id).findFirst()!!.deleteFromRealm()
        }
    }

    fun getProductByCategory(item: String) = realm.where(Product::class.java).equalTo("productCategory", item).findAll()!!
}