package com.sobkisu.store.db

import com.sobkisu.store.SobKisuApplication
import com.sobkisu.store.model.Product
import com.sobkisu.store.model.ProductTransaction
import com.sobkisu.store.model.active
import com.sobkisu.store.model.deleted
import io.realm.Realm

class ProductRepository {
    val realm: Realm = Realm.getInstance(SobKisuApplication().getTestConfigaration())


    fun saveProduct(item: Product): Boolean {
        try {
            realm.beginTransaction()
            var max = realm.where(Product::class.java).findAll().size
            item.Id = max.toLong() + 1
            item.status = active
            realm.copyToRealm(item)
            realm.copyToRealm(ProductTransaction(Id = item.Id, productId = item.Id))

            realm.commitTransaction()
            return true
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return false
    }

    fun getProductById(item: Long) = realm.where(Product::class.java).equalTo("Id", item).and().equalTo("status", active).findFirst()!!

    fun getAllProduct() = realm.where(Product::class.java).and().equalTo("status", active).findAll()!!

    fun deleteProductById(id: Long): Boolean {
        try {
            realm.executeTransaction {
                it.where(Product::class.java).equalTo("Id", id).and().equalTo("status", active).findFirst()!!.status = deleted
            }
        } catch (e: Exception) {

            e.printStackTrace()
        }

        return true
    }

}