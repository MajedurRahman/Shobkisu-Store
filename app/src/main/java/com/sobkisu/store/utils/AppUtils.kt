package com.sobkisu.store.utils

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Base64
import jp.wasabeef.picasso.transformations.RoundedCornersTransformation
import java.io.ByteArrayOutputStream
import java.io.File


class AppUtils {


    fun getImageBitmapFromPath(path: String): Bitmap? {
        val imgFile = File(path)
        return if (imgFile.exists()) {
            val myBitmap = BitmapFactory.decodeFile(imgFile.absolutePath)
            myBitmap
        } else {
            null
        }
    }

    fun convertImageToString(bitmap: Bitmap): String? {
        val baos = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.JPEG, 50, baos)
        val imageBytes = baos.toByteArray()
        log(Base64.encodeToString(imageBytes, Base64.DEFAULT))
        return Base64.encodeToString(imageBytes, Base64.DEFAULT)

    }


    fun convertStringToImage(item: String): Bitmap? {
        val baos = ByteArrayOutputStream()
        val imageBytes = decode(item)
        return BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.size)
    }


    private fun decode(base64String: String): ByteArray {
        return Base64.decode(base64String, Base64.DEFAULT)
    }

    companion object {
        fun getRondedTransformaion5() {
            val radius = 5
            val margin = 5
            val transformation = RoundedCornersTransformation(radius, margin)

        }
    }


}