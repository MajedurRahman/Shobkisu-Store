package com.sobkisu.store.db

import android.util.Log
import com.sobkisu.store.SobKisuApplication
import com.sobkisu.store.model.*
import com.sobkisu.store.utils.log
import io.realm.Realm


class BuySellRepository {
    val realm: Realm = Realm.getInstance(SobKisuApplication().getTestConfigaration())


    fun saveSellProduct(item: Product, count: Int): Boolean {
        var isAllright = false
        realm.executeTransaction {
            val id = it.where(Sell::class.java).findAll().size

            val productTrans = it.where(ProductTransaction::class.java).equalTo("productId", item.Id).findFirst()
            if (productTrans!!.productAvailable!! >= count) {
                it.copyToRealm(Sell(Id = id.toLong() + 1, productId = item.Id, sellPrice = item.productPrice, status = active, numberOfItem = 1))
                updateSellProductTransaction(item.Id!!, count, it)

                isAllright = true
            }
        }
        realm.where(Sell::class.java).findAll().forEach {
            Log.e("Sell Data ", " Product Id " + it.productId.toString() + " - Sell  ID " + it.Id)
        }
        return isAllright
    }

    fun saveBuyProduct(item: Product, count: Int) {
        realm.executeTransaction {
            val id = it.where(Buy::class.java).findAll().size
            it.copyToRealm(Buy(Id = id.toLong() + 1, productId = item.Id, sellPrice = item.productPrice, status = active, numberOfItem = count))
        }
        realm.where(Buy::class.java).findAll().forEach {
            Log.e("Buy Data ", " Product Id " + it.productId.toString() + " - Buy  ID " + it.Id)
        }
        item.Id?.let { updateBuyProductTransaction(it, count) }
    }

    fun getAvailableProduct(id: Long): Int {
        val allProductSize = realm.where(Buy::class.java).equalTo("productId", id).and().equalTo("status", active).findAll().size
        log("Total Buy :$allProductSize")
        val allSellProductSize = realm.where(Sell::class.java).equalTo("productId", id).and().equalTo("status", active).findAll().size
        log("Total Sell :$allSellProductSize")
        return allProductSize - allSellProductSize
    }


    fun getTransacByProductId(id: Long) = realm.where(ProductTransaction::class.java).equalTo("productId", id).and().equalTo("status", active).findFirst()!!

    private fun updateBuyProductTransaction(id: Long, count: Int) {
        realm.executeTransaction {
            var itemTrans = it.where(ProductTransaction::class.java).equalTo("productId", id).and().equalTo("status", active).findFirst()!!
            if (itemTrans != null) {
                itemTrans.productAvailable = itemTrans.productAvailable?.plus(count)
                it.copyToRealmOrUpdate(itemTrans)
            }
        }
        realm.where(ProductTransaction::class.java).findAll().forEach {
            log(" Product Id :${it.productId} TransID : ${it.Id} Available :  ${it.productAvailable}  Sold: ${it.productSold} ")
        }
    }

    private fun updateSellProductTransaction(id: Long, count: Int, it: Realm) {

        val itemTrans = it.where(ProductTransaction::class.java).equalTo("productId", id).and().equalTo("status", active).findFirst()!!
        if (itemTrans != null) {
            itemTrans.productSold = itemTrans.productSold?.plus(count)
            itemTrans.productAvailable = itemTrans.productAvailable?.minus(count)
            it.copyToRealmOrUpdate(itemTrans)
        }

        it.where(ProductTransaction::class.java).findAll().forEach {
            log(" Product Id :${it.productId} TransID : ${it.Id} Available :  ${it.productAvailable}  Sold: ${it.productSold} ")
        }
    }


    fun getAllBuySellDetails(): ArrayList<BuySellInfo> {
        val buySellData = ArrayList<BuySellInfo>()
        realm.where(Buy::class.java).findAll().forEach {
            buySellData.add(BuySellInfo(productId = it.productId, buySellType = 1, productPrice = it.sellPrice, productCount = it.numberOfItem, createdAt = it.createdAt))
        }
        realm.where(Sell::class.java).findAll().forEach {
            buySellData.add(BuySellInfo(productId = it.productId, buySellType = 0, productPrice = it.sellPrice, productCount = it.numberOfItem, createdAt = it.createdAt))
        }
        buySellData.sortByDescending {
            it.createdAt
        }

        return buySellData
    }
}