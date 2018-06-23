package com.sobkisu.store.db

import com.esafirm.imagepicker.model.Image
import com.sobkisu.store.SobKisuApplication
import com.sobkisu.store.model.*
import com.sobkisu.store.utils.AppUtils
import com.sobkisu.store.utils.log
import io.realm.Realm

class ProductRepository {
    val realm: Realm = Realm.getInstance(SobKisuApplication().getTestConfiguration())
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
    fun getProductByIdAll(item: Long) = realm.where(Product::class.java).equalTo("Id", item).findFirst()!!
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

    fun saveProductImage(productId: Long?, images: MutableList<Image>) {

        if (productId != null) {
            realm.executeTransactionAsync {
                val max = it.where(ProductImage::class.java).findAll().size
                for (data in images) {
                    val imageString = AppUtils().convertImageToString(AppUtils().getImageBitmapFromPath(data.path)!!)
                    log("Image Converted Successfully " + data.name)
                    it.copyToRealm(ProductImage(Id = max.toLong() + 1, productId = productId, imageName = data.name, imageString = imageString))
                }
            }
        }

    }

    fun getProductImageByProductId(productId: Long?): ProductImage? = realm.where(ProductImage::class.java).equalTo("Id", productId).equalTo(status, active).findFirst()


}