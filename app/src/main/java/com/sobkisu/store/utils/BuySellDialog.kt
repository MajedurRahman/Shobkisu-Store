package com.sobkisu.store.utils

import android.app.Dialog
import android.content.Context
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.sobkisu.store.R
import com.sobkisu.store.db.BuySellRepository
import com.sobkisu.store.db.ProductRepository
import com.sobkisu.store.model.Product

class BuySellDialog(val context: Context) {
    fun buyDialog(productId: Long?, view: View? = null) {
        try {
            var dialog = Dialog(context)
            dialog.setContentView(R.layout.buy_sell_dialog)
            dialog.show()
            var productItem = ProductRepository().getProductById(productId!!)
            log("Item " + productItem.productName + " Price " + productItem.productPrice)
            dialog.findViewById<TextView>(R.id.dialogTitle).text = "${productItem.productName}"
            var priceEt = dialog.findViewById<EditText>(R.id.buyPriceEditText)
            var countItemEt = dialog.findViewById<EditText>(R.id.totalProduct)
            priceEt.setText(productItem.productPrice!!.toString())
            countItemEt.setText(1.toString())

            var buyButton = dialog.findViewById<Button>(R.id.createDialogSave)
            var cancelButton = dialog.findViewById<Button>(R.id.createDialogCancel)

            buyButton.setOnClickListener {
                val price = priceEt.text
                val count = countItemEt.text
                if (!price.toString().isEmpty() && !count.toString().isEmpty()) {
                    val product = Product(Id = productItem.Id, productCategory = productItem.productCategory, productName = productItem.productName, productPrice = price.toString().toInt())
                    BuySellRepository().saveBuyProduct(product, count.toString().toInt())

                    if (view == null) {
                        context.positiveSnackBar("Successfully Saved Buy Data")
                    } else {
                        context.positiveSnackBar("Successfully Saved Buy Data", view)

                    }
                    dialog.dismiss()
                } else {
                    context.negativeSnackBar("Missing Information ")
                }
            }
            cancelButton.setOnClickListener {
                dialog.dismiss()
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun sellDialog(productId: Long?, view: View? = null) {
        try {
            var dialog = Dialog(context)
            dialog.setContentView(R.layout.buy_sell_dialog)
            dialog.show()
            var productItem = ProductRepository().getProductById(productId!!)
            log("Item " + productItem.productName + " Price " + productItem.productPrice)
            dialog.findViewById<TextView>(R.id.dialogTitle).text = "${productItem.productName}"
            var priceEt = dialog.findViewById<EditText>(R.id.buyPriceEditText)
            var countItemEt = dialog.findViewById<EditText>(R.id.totalProduct)
            priceEt.setText(productItem.productPrice!!.toString())
            countItemEt.setText(1.toString())

            var buyButton = dialog.findViewById<Button>(R.id.createDialogSave)
            var cancelButton = dialog.findViewById<Button>(R.id.createDialogCancel)

            buyButton.setOnClickListener {
                val price = priceEt.text
                val count = countItemEt.text
                if (!price.toString().isEmpty() && !count.toString().isEmpty()) {
                    val product = Product(Id = productItem.Id, productCategory = productItem.productCategory, productName = productItem.productName, productPrice = price.toString().toInt())
                    if (BuySellRepository().saveSellProduct(product, count.toString().toInt())) {

                        if (view == null) {
                            context.positiveSnackBar("Successfully Saved Sell Data")
                        } else {
                            context.positiveSnackBar("Successfully Saved Sell Data", view)
                        }
                    } else {
                        if (view == null) {
                            context.negativeSnackBar("Total $count ${productItem.productName} Is Not Available ")
                        } else {
                            context.negativeSnackBar("Total $count ${productItem.productName} Is Not Available ", view)
                        }
                    }
                    dialog.dismiss()
                } else {
                    if (view == null) {
                        context.negativeSnackBar("Missing Information")
                    } else {
                        context.negativeSnackBar("Missing Information", view)
                    }
                }
            }
            cancelButton.setOnClickListener {
                dialog.dismiss()
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}