package com.example.aston_intensive_6

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.MotionEvent
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import com.example.aston_intensive_6.data.Contact
import com.example.aston_intensive_6.databinding.ContactsListItemBinding


class ContactsListItem @JvmOverloads constructor(
    context:Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    private var _binding: ContactsListItemBinding? = null
    private val binding get() = _binding!!
    private var touchType = -1

    private lateinit var contact: Contact

    init {
        _binding = ContactsListItemBinding.inflate(LayoutInflater.from(context), this, false)
    }
}