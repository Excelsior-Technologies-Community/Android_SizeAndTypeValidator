package com.ext.sizetypevalidator.core

import com.ext.sizetypevalidator.model.ValidationResult

class StringValidator private constructor(
    private val value: String
) {

    private var minLength: Int? = null
    private var maxLength: Int? = null
    private var allowBlank: Boolean = true

    companion object {
        fun from(value: String): StringValidator {
            return StringValidator(value)
        }
    }

    fun minLength(length: Int) = apply {
        this.minLength = length
    }

    fun maxLength(length: Int) = apply {
        this.maxLength = length
    }

    fun allowBlank(allow: Boolean) = apply {
        this.allowBlank = allow
    }

    fun validate(): ValidationResult {

        if (!allowBlank && value.isBlank()) {
            return ValidationResult.Error("String cannot be blank")
        }

        minLength?.let {
            if (value.length < it) {
                return ValidationResult.Error(
                    "String length ${value.length} is less than minimum $it"
                )
            }
        }

        maxLength?.let {
            if (value.length > it) {
                return ValidationResult.Error(
                    "String length ${value.length} exceeds maximum $it"
                )
            }
        }

        return ValidationResult.Success
    }
}
