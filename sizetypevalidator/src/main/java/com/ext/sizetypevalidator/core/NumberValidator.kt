package com.ext.sizetypevalidator.core

import com.ext.sizetypevalidator.model.ValidationResult

class NumberValidator private constructor(
    private val value: Number
) {

    private var min: Double? = null
    private var max: Double? = null

    companion object {
        fun from(value: Number): NumberValidator {
            return NumberValidator(value)
        }
    }

    fun min(value: Number) = apply {
        this.min = value.toDouble()
    }

    fun max(value: Number) = apply {
        this.max = value.toDouble()
    }

    fun validate(): ValidationResult {
        val number = value.toDouble()

        min?.let {
            if (number < it) {
                return ValidationResult.Error(
                    "Value $number is less than minimum $it"
                )
            }
        }

        max?.let {
            if (number > it) {
                return ValidationResult.Error(
                    "Value $number exceeds maximum $it"
                )
            }
        }

        return ValidationResult.Success
    }
}
