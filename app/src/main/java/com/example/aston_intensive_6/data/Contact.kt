package com.example.aston_intensive_6.data

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Color
import android.util.Base64
import java.io.ByteArrayOutputStream
import java.io.Serializable
import kotlin.random.Random

data class Contact(
    var name: String,
    var number: String,
) : Serializable {
    private var avatar: String? = null
    var initials: String = ""
    var backgroundColor: Int = 0

    init {
        if (name.last().isWhitespace())
            name = name.dropLast(1)
        for (c in name.split(" ")) {
            initials += c[0].uppercase()
        }
        val rnd = Random
        backgroundColor = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256))
    }

    fun encodeAvatar(bitmap: Bitmap) {
        val outputStream = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream)
        val b = outputStream.toByteArray()
        avatar = Base64.encodeToString(b, Base64.DEFAULT)
    }

    fun decodeAvatar(): Bitmap? {
        if (avatar == null)
            return null
        val b = Base64.decode(avatar, Base64.DEFAULT)
        return BitmapFactory.decodeByteArray(b, 0, b.size)
    }
}
