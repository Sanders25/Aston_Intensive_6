package com.example.aston_intensive_6

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.Base64
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResultListener
import com.example.aston_intensive_6.data.Contact
import com.example.aston_intensive_6.databinding.ContactDescriptionBinding
import com.google.android.material.imageview.ShapeableImageView
import java.io.Serializable

private const val STATE_CONTACT = "STATE_CONTACT"

const val RETURNED_CONTACT = "RETURNED_CONTACT"

class ContactDescription : Fragment(R.layout.contact_description) {
    private var _binding: ContactDescriptionBinding? = null
    private val binding get() = _binding!!

    private lateinit var contact: Contact

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setFragmentResultListener(TO_DESC) { _, bundle ->
            contact = bundle.getSerializable(CONTACT) as Contact
            populateDescription()
            parentFragmentManager.clearFragmentResultListener(TO_DESC)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = ContactDescriptionBinding.inflate(inflater, container, false)
        val view = binding.root

        if (savedInstanceState != null) {
            contact = savedInstanceState.getSerializable(STATE_CONTACT) as Contact
            populateDescription()
        }

        binding.buttonSave.setOnClickListener {
            saveChanges()
        }
        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun populateDescription() {
        val btnBackground = ContextCompat.getDrawable(requireContext(), R.drawable.btn_shape)
        btnBackground?.setTint(contact.backgroundColor)
        binding.buttonSave.background = btnBackground
        binding.name.setText(contact.name)
        binding.number.setText(contact.number)
        val background = ContextCompat.getDrawable(requireContext(),R.drawable.rounded_textview)
        binding.initials.background = background
        background?.setTint(contact.backgroundColor)

        val avatarImageView = binding.avatar
        val initialsView = binding.initials
        val avatar = contact.decodeAvatar()

        if(avatar != null) {
            avatarImageView.setImageBitmap(avatar)
        }
        else {
            initialsView.visibility = View.VISIBLE
            avatarImageView.visibility = View.GONE
            initialsView.text = contact.initials
        }
    }

    private fun saveChanges() {
        val bundle = Bundle()

        val newContact = Contact(
            name = binding.name.text.toString(),
            number = binding.number.text.toString()
        )

        bundle.putSerializable(RETURNED_CONTACT, newContact as Serializable)

        parentFragmentManager.setFragmentResult(FROM_DESC, bundle)
        parentFragmentManager.popBackStack()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        outState.putSerializable(STATE_CONTACT, contact as Serializable)
    }
}