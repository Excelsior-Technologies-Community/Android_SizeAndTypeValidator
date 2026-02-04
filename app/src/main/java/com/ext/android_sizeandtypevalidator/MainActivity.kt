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
        val picker =
            registerForActivityResult(ActivityResultContracts.GetContent()) { uri ->
                if (uri != null) {
                    val result = FileValidator
                        .from(this, uri)
                        .maxSizeMB(5.0)
                        .allowTypes(FileType.IMAGE)
                        .maxImageResolution(
                            maxWidth = 1000,
                            maxHeight = 1000
                        )
                        .validate()

                    when (result) {
                        is ValidationResult.Success ->
                            Log.d("Validator", "✅ URI validation success")

                        is ValidationResult.Error ->
                            Log.e("Validator", "❌ ${result.message}")
                    }
                }
            }
        findViewById<Button>(R.id.btnPickImage).setOnClickListener {
            picker.launch("image/*")
        }
    }
}