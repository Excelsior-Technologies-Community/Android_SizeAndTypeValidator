package com.ext.sizetypevalidator.model

enum class FileType(val extensions: List<String>) {
    IMAGE(listOf("jpg", "jpeg", "png", "webp")),
    VIDEO(listOf("mp4", "mkv", "avi")),
    DOCUMENT(listOf("pdf", "doc", "docx")),
    AUDIO(listOf("mp3", "wav"))
}