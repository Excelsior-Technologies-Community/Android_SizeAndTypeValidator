package com.ext.sizetypevalidator.core

import android.content.Context
import android.net.Uri
import android.util.Log
import com.ext.sizetypevalidator.model.FileType
import com.ext.sizetypevalidator.model.ValidationResult
import com.ext.sizetypevalidator.util.FileUtils
import com.ext.sizetypevalidator.util.ImageUtils
import com.ext.sizetypevalidator.util.UriUtils
import java.io.File

class FileValidator private constructor(
    private val file: File
) {

    private var maxSizeMB: Double? = null
    private var allowedTypes: List<FileType>? = null
    private var maxImageWidth: Int? = null
    private var maxImageHeight: Int? = null


    companion object {

        fun from(file: File): FileValidator {
            return FileValidator(file)
        }

        fun from(context: Context, uri: Uri): FileValidator {
            return try {
                val file = UriUtils.uriToFile(context, uri)
                FileValidator(file)
            } catch (e: Exception) {
                throw IllegalArgumentException(
                    "Invalid or inaccessible URI provided",
                    e
                )
            }
        }
    }



    fun maxSizeMB(size: Double) = apply {
        this.maxSizeMB = size
    }

    fun allowTypes(vararg types: FileType) = apply {
        this.allowedTypes = types.toList()
    }

    fun validate(): ValidationResult {
        maxSizeMB?.let {
            val size = FileUtils.sizeInMB(file)
            if (size > it) {
                return ValidationResult.Error("File size exceeds $it MB")
            }
        }

        allowedTypes?.let { types ->
            val ext = FileUtils.getExtension(file)
            Log.d("Validator", "Detected extension: .$ext")
            val allowed = types.any { it.extensions.contains(ext) }
            if (!allowed) {
                return ValidationResult.Error("File type .$ext is not allowed")
            }
        }

        maxImageWidth?.let { maxW ->
            maxImageHeight?.let { maxH ->
                val dimensions = ImageUtils.getImageDimensions(file)
                    ?: return ValidationResult.Error("Unable to read image dimensions")

                val (width, height) = dimensions

                if (width > maxW || height > maxH) {
                    return ValidationResult.Error(
                        "Image resolution ${width}x${height} exceeds allowed ${maxW}x${maxH}"
                    )
                }
            }
        }


        return ValidationResult.Success
    }
    fun maxImageResolution(
        maxWidth: Int,
        maxHeight: Int
    ) = apply {
        this.maxImageWidth = maxWidth
        this.maxImageHeight = maxHeight
    }

}
