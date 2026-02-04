package com.ext.sizetypevalidator.util

import java.io.File

object FileUtils {

    fun getExtension(file: File): String {
        return file.extension.lowercase()
    }

    fun sizeInMB(file: File): Double {
        return file.length() / (1024.0 * 1024.0)
    }
}