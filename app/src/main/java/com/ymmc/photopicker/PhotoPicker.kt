package com.ymmc.photopicker

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.PickVisualMediaRequest
import androidx.appcompat.app.AppCompatActivity
import androidx.activity.result.contract.ActivityResultContracts.PickVisualMedia
import com.ymmc.photopicker.databinding.ActivityPhotoPickerBinding

class PhotoPicker : AppCompatActivity() {

    private lateinit var binding: ActivityPhotoPickerBinding
    private lateinit var pickMedia: ActivityResultLauncher<PickVisualMediaRequest>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPhotoPickerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        registerPickMedia()
        binding.myButton.setOnClickListener {
            Toast.makeText(this, "Pick photo", Toast.LENGTH_SHORT).show()
            pickMedia()
        }

    }

    private fun pickMedia() {
        pickMedia.launch(PickVisualMediaRequest(PickVisualMedia.ImageOnly))
    }

    private fun registerPickMedia() {
        pickMedia = registerForActivityResult(PickVisualMedia()) { uri ->
            if (uri != null) {
                binding.imageView.setImageURI(uri)
            } else {
                Log.d("PhotoPicker", "No media selected")
            }
        }
    }
}