package com.ext.sizetypevalidator.util

import android.content.Context
import android.net.Uri
import android.webkit.MimeTypeMap
import java.io.File
import java.io.FileOutputStream

object UriUtils {

    fun uriToFile(context: Context, uri: Uri): File {
        val contentResolver = context.contentResolver

        // 1️⃣ Get MIME type
        val mimeType = contentResolver.getType(uri)

        // 2️⃣ Convert MIME → extension
        val extension = MimeTypeMap.getSingleton()
            .getExtensionFromMimeType(mimeType) ?: ""

        // 3️⃣ Create proper temp file name
        val fileName = if (extension.isNotEmpty()) {
            "temp_file.$extension"
        } else {
            "temp_file"
        }

        val tempFile = File(context.cacheDir, fileName)

        contentResolver.openInputStream(uri)?.use { input ->
            FileOutputStream(tempFile).use { output ->
                input.copyTo(output)
            }
        } ?: throw IllegalArgumentException("Unable to open URI")

        return tempFile
    }
}
