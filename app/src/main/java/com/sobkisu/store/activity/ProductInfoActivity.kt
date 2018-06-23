package com.sobkisu.store.activity

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.esafirm.imagepicker.features.ImagePicker
import com.esafirm.imagepicker.features.ReturnMode
import com.esafirm.imagepicker.model.Image
import com.sobkisu.store.R
import com.sobkisu.store.SobKisuApplication
import com.sobkisu.store.utils.log
import io.realm.Realm


class ProductInfoActivity : AppCompatActivity() {
    val realm: Realm = Realm.getInstance(SobKisuApplication().getTestConfiguration())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_info)
        initLayout()
    }

    private fun initLayout() {
        setUpApplicationCategory()
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

    private fun getManageCategoryList() {

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (ImagePicker.shouldHandle(requestCode, resultCode, data)) {
            val images: MutableList<Image>? = ImagePicker.getImages(data)
            images!!.forEach {
                log(" All Image List : ".plus(it.path))
            }
        }
        super.onActivityResult(requestCode, resultCode, data)
    }
}
