package com.dicoding.asclepius.view

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.dicoding.asclepius.R
import com.dicoding.asclepius.databinding.ActivityResultBinding

class ResultActivity : AppCompatActivity() {
    private lateinit var binding: ActivityResultBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResultBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // TODO: Menampilkan hasil gambar, prediksi, dan confidence score.
        val label = intent.getStringExtra("LABEL") ?: "Unknown"
        val score = intent.getFloatExtra("SCORE", 0f)
        val inferenceTime = intent.getLongExtra("INFERENCE_TIME", 0)
        val imageUriString = intent.getStringExtra("IMAGE_URI")

        displayResults(label, score, inferenceTime)
        displayImage(imageUriString)
    }

    private fun displayResults(label: String, score: Float, inferenceTime: Long) {
        val resultText = """
            Prediction: $label
            Confidence: ${String.format("%.2f%%", score * 100)}
            Inference Time: $inferenceTime ms
        """.trimIndent()

        binding.resultText.text = resultText
    }

    private fun displayImage(imageUriString: String?) {
        imageUriString?.let { uriString ->
            val imageUri = Uri.parse(uriString)
            binding.resultImage.setImageURI(imageUri)
        }
    }
}