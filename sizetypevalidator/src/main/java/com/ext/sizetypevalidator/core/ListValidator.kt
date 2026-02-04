package com.ext.sizetypevalidator.core

import com.ext.sizetypevalidator.model.ValidationResult

class ListValidator<T> private constructor(
    private val list: List<T>
) {

    private var minSize: Int? = null
    private var maxSize: Int? = null
    private var allowEmpty: Boolean = true

    companion object {
        fun <T> from(list: List<T>): ListValidator<T> {
            return ListValidator(list)
        }
    }

    fun minSize(size: Int) = apply {
        this.minSize = size
    }

    fun maxSize(size: Int) = apply {
        this.maxSize = size
    }

    fun allowEmpty(allow: Boolean) = apply {
        this.allowEmpty = allow
    }

    fun validate(): ValidationResult {

        if (!allowEmpty && list.isEmpty()) {
            return ValidationResult.Error("List cannot be empty")
        }

        minSize?.let {
            if (list.size < it) {
                return ValidationResult.Error(
                    "List size ${list.size} is less than minimum $it"
                )
            }
        }

        maxSize?.let {
            if (list.size > it) {
                return ValidationResult.Error(
                    "List size ${list.size} exceeds maximum $it"
                )
            }
        }

        return ValidationResult.Success
    }
}
