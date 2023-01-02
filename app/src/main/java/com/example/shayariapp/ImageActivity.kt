package com.example.shayariapp

import android.app.Dialog
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.drawToBitmap
import com.example.shayariapp.databinding.ActivityImageBinding
import java.io.File
import java.io.FileOutputStream

class ImageActivity : AppCompatActivity() {
    var key1 = 0
    lateinit var cam: Bitmap
    lateinit var uri: Uri
    var key2 = 1
    var imageArray = arrayOf(
        R.drawable.ig01,
        R.drawable.ig02,
        R.drawable.ig03,
        R.drawable.ig04,
        R.drawable.ig05,
        R.drawable.ig06
    )

    val bg =
        intArrayOf(R.color.red, R.color.dark, R.color.yellow, R.color.yellow, R.color.ornage)
    var d = 0

    lateinit var binding: ActivityImageBinding
    var count = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityImageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initvtw()

    }

    private fun initvtw() {

        binding.txtShayari.text = intent.getStringExtra("quoteKey")


        binding.imgbg.setImageResource(imageArray.get(0))

        binding.imgbg.setOnClickListener {
            if (count > 5) {
                count = 0
            }
            binding.imgbg.setImageResource(imageArray.get(count))
            count++
        }
        binding.txtShayari.setOnClickListener { v ->

            binding.txtShayari.setTextColor(resources.getColor(bg.get(d)))
            d++
            if (d == 4) {
                d = 0
            }
        }

        binding.imgedt.setOnClickListener {
            val dialog = Dialog(this)
            dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            dialog.setContentView(R.layout.photo_dialog_box)
            val imgPhoto = dialog.findViewById<View>(R.id.imgPhoto)
            val imgCamera = dialog.findViewById<View>(R.id.imgCamera)
            imgPhoto.setOnClickListener {
                val galary = Intent(
                    Intent.ACTION_PICK,
                    MediaStore.Images.Media.EXTERNAL_CONTENT_URI
                )
                startActivityForResult(Intent.createChooser(galary, "Select Image"), key1)
                dialog.dismiss()
            }
            imgCamera.setOnClickListener {
                val camera = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                startActivityForResult(camera, key2)
                dialog.dismiss()
            }
            dialog.show()
        }


        binding.backicon.setOnClickListener {
            finish()
        }
        binding.save.setOnClickListener {
            saveimage()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == key1) {
            if (resultCode == -1 && data != null) {
                uri = data.data!!
                binding.imgbg.setImageURI(uri)
            } else {
                Toast.makeText(this, "Anything else", Toast.LENGTH_SHORT).show()
            }
        } else if (requestCode == key2) {
            if (resultCode == -1 && data != null) {
                cam = (data.extras!!["data"] as Bitmap?)!!

                binding.imgbg.setImageBitmap(cam)
            } else {
                Toast.makeText(this, "failure", Toast.LENGTH_SHORT).show()
            }
        } else {
            Toast.makeText(this, "No Image Clicked", Toast.LENGTH_SHORT).show()
        }
    }

    private fun saveimage() {

        var bitmap = binding.imageCapture.drawToBitmap()
        var foutStream: FileOutputStream? = null
        var sdCard = Environment.getExternalStorageDirectory()
        var dir = File(sdCard.absolutePath + "/DCIM/Camera")
        dir.mkdir()
        var fileName = String.format("%d.jpg", System.currentTimeMillis())
        var outfile = File(dir, fileName)
        foutStream = FileOutputStream(outfile)
        bitmap.compress(Bitmap.CompressFormat.JPEG, 99, foutStream)
        foutStream.flush()
        foutStream.close()
        Toast.makeText(this, "Image saved", Toast.LENGTH_SHORT).show()
        var intent = Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE)
        intent.data = Uri.fromFile(outfile)
        sendBroadcast(intent)

    }


}