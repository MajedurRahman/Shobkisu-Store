package com.sobkisu.store.activity

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.esafirm.imagepicker.features.ImagePicker
import com.esafirm.imagepicker.features.ReturnMode
import com.esafirm.imagepicker.model.Image
import com.sobkisu.store.R
import com.sobkisu.store.db.ProductRepository
import kotlinx.android.synthetic.main.activity_product_details.*

class ProductDetailsActivity : AppCompatActivity() {
    var productId: Long? = null;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_details)



        choosePhoto.setOnClickListener {
            getProductDetails()
        }

    }

    private fun getProductDetails() {
        val id = intent.getLongExtra("productId", (-1).toLong())
        this.productId = id
        if (id != (-1).toLong()) {
            val product = ProductRepository().getProductById(id)
            setUpApplicationCategory()
        }
    }

    private fun setUpApplicationCategory() {
        try {
            ImagePicker.create(this)
                    .returnMode(ReturnMode.NONE) // set whether pick and / or camera action should return immediate result or not.
                    .folderMode(true)
                    .theme(R.style.AppTheme) // must inherit ef_BaseTheme. please refer to sample
                    .toolbarFolderTitle("Folder") // folder selection title
                    .toolbarImageTitle("Tap to select") // image selection title
                    .toolbarArrowColor(Color.WHITE) // Toolbar 'up' arrow color
                    .multi()
                    .showCamera(true) // show camera or not (true by default)
                    .imageDirectory("Camera") // directory name for captured image  ("Camera" folder by default)
                    .enableLog(false) // disabling log
                    .start()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (ImagePicker.shouldHandle(requestCode, resultCode, data)) {
            val images: MutableList<Image>? = ImagePicker.getImages(data)

            images!!.forEach {
                ProductRepository().saveProductImage(productId, images)
            }
        }
        super.onActivityResult(requestCode, resultCode, data)
    }


}
