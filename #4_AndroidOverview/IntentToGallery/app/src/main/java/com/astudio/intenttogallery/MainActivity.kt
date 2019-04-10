package com.astudio.intenttogallery

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import kotlinx.android.synthetic.main.activity_main.*

/**
 * The class is used for launched application and implement simple activity
 * It navigates to device photo gallery, chooses any image and displayes selected image in ImageView on activity screen
 * Handles events as click Button.
 *
 * @author Anna Zholud
 */

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button.setOnClickListener {
            openImageGallery()
        }
    }

    private fun openImageGallery() {
        val photoPickerIntent = Intent(Intent.ACTION_PICK)
        photoPickerIntent.type = PICKER_TYPE
        startActivityForResult(photoPickerIntent, GALLERY_REQUEST)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, imageReturnedIntent: Intent?) {
        super.onActivityResult(requestCode, resultCode, imageReturnedIntent)

        if (requestCode == GALLERY_REQUEST && resultCode == Activity.RESULT_OK) {
            imageReturnedIntent?.data?.let {
                val bitmap = MediaStore.Images.Media.getBitmap(contentResolver, it)
                imageView.setImageBitmap(bitmap)
            }
        }
    }

    private companion object {
        private const val GALLERY_REQUEST = 1
        private const val PICKER_TYPE = "image/*"
    }
}
