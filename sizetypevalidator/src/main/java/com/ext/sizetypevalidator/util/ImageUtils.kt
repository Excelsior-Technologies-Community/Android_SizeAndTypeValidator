package com.ext.sizetypevalidator.util

import android.graphics.BitmapFactory
import java.io.File

object ImageUtils {

    fun getImageDimensions(file: File): Pair<Int, Int>? {
        val options = BitmapFactory.Options().apply {
            inJustDecodeBounds = true
        }

        BitmapFactory.decodeFile(file.absolutePath, options)

        return if (options.outWidth > 0 && options.outHeight > 0) {
            options.outWidth to options.outHeight
        } else {
            null
        }
    }
}
