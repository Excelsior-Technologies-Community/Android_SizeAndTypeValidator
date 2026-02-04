package com.ext.android_sizeandtypevalidator

import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.ext.sizetypevalidator.core.FileValidator
import com.ext.sizetypevalidator.core.ListValidator
import com.ext.sizetypevalidator.model.FileType
import com.ext.sizetypevalidator.model.ValidationResult
import java.io.File

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val result = ListValidator
            .from(listOf(1, 2, 3))
            .minSize(2)
            .maxSize(5)
            .allowEmpty(false)
            .validate()

        when (result) {
            is ValidationResult.Success ->
                Log.i("ListValidator", "✅ List validation success")

            is ValidationResult.Error ->
                Log.e("ListValidator", "❌ ${result.message}")
        }

    }
}