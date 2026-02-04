package com.ext.sizetypevalidator.util

import android.media.MediaMetadataRetriever
import java.io.File

object VideoUtils {

    fun getVideoDurationSeconds(file: File): Long? {
        val retriever = MediaMetadataRetriever()

        return try {
            retriever.setDataSource(file.absolutePath)

            val durationMs =
                retriever.extractMetadata(
                    MediaMetadataRetriever.METADATA_KEY_DURATION
                )?.toLong()

            durationMs?.div(1000)
        } catch (e: Exception) {
            null
        } finally {
            retriever.release()
        }
    }
}
