package com.ext.sizetypevalidator.validator

import com.ext.sizetypevalidator.model.ValidationResult
import java.io.File

class SizeValidator(
    private val maxSizeMB: Double
) {

    fun validate(file: File): ValidationResult {
        val sizeMB = file.length() / (1024.0 * 1024.0)

        return if (sizeMB <= maxSizeMB) {
            ValidationResult.Success
        } else {
            ValidationResult.Error(
                "File size ${"%.2f".format(sizeMB)}MB exceeds limit of $maxSizeMB MB"
            )
        }
    }
}