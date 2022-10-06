package com.example.aston_intensive_6

import android.graphics.Bitmap
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filterable
import android.widget.PopupMenu
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.bumptech.glide.signature.ObjectKey
import com.example.aston_intensive_6.data.Contact
import com.example.aston_intensive_6.databinding.ContactsListItemBinding
import kotlin.math.roundToInt


class CustomAdapter(
    private val dataSet: List<Contact>,
    val onItemClick: (contact: Contact, index: Int) -> Unit,
    val onDeleteClick: (contact: Contact) -> Unit
) :
    RecyclerView.Adapter<CustomAdapter.ViewHolder>() {

    inner class ViewHolder(private val binding: ContactsListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(contact: Contact, position: Int) {
            val r = itemView.context.resources
            val imageSize =
                TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 50f, r.displayMetrics)
                    .roundToInt()
            val url =
                "https://picsum.photos/id/$position/$imageSize"
            //"https://random.imagecdn.app/$imageSize/$imageSize/?temp=$position"
            val drawable = CircularProgressDrawable(itemView.context)

            drawable.centerRadius = 30f
            drawable.strokeWidth = 5f
            drawable.start()

            Glide.with(itemView.context)
                .asBitmap()
                .load(url)
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .signature(ObjectKey(System.currentTimeMillis()))
                .skipMemoryCache(true)
                .placeholder(drawable)
                .listener(object : RequestListener<Bitmap> {
                    override fun onResourceReady(
                        resource: Bitmap,
                        model: Any?,
                        target: Target<Bitmap>?,
                        dataSource: DataSource?,
                        isFirstResource: Boolean
                    ): Boolean {
                        assignAvatarToContact(contact, resource, binding)
                        return false
                    }

                    override fun onLoadFailed(
                        e: GlideException?,
                        model: Any?,
                        target: Target<Bitmap>?,
                        isFirstResource: Boolean
                    ): Boolean {
                        assignAvatarToContact(contact, null, binding)
                        return false
                    }
                })
                .into(binding.avatar)

            binding.name.text = contact.name
            binding.number.text = contact.number

            val background =
                ContextCompat.getDrawable(itemView.context, R.drawable.rounded_textview)
            binding.initials.background = background
            background?.setTint(contact.backgroundColor)


            binding.popupMenu.setOnClickListener {

            }
            itemView.setOnLongClickListener {
                val menu = PopupMenu(itemView.context, itemView)
                menu.inflate(R.menu.options_menu)
                menu.setOnMenuItemClickListener {
                    when (it.itemId) {
                        R.id.action_delete -> {
                            onDeleteClick(contact)
                            notifyItemRemoved(position)
                            notifyItemRangeChanged(position, dataSet.size);
                            true
                        }
                        else -> false
                    }
                }
                val field = menu::class.java.getDeclaredField("mPopup")
                field.isAccessible = true
                val mPopup = field.get(menu)
                mPopup.javaClass.getDeclaredMethod("setForceShowIcon", Boolean::class.java)
                    .invoke(mPopup, true)
                menu.show()
                true
            }
            itemView.setOnClickListener {
                onItemClick(contact, dataSet.indexOf(contact))
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ContactsListItemBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(dataSet[position], position)
    }

    override fun getItemCount(): Int = dataSet.size

    private fun assignAvatarToContact(
        contact: Contact,
        avatar: Bitmap?,
        binding: ContactsListItemBinding
    ) {

        val avatarImageView = binding.avatar
        val initialsView = binding.initials

        if (avatar != null) {
            contact.encodeAvatar(avatar)
            avatarImageView.setImageBitmap(avatar)
        } else {
            initialsView.visibility = View.VISIBLE
            avatarImageView.visibility = View.GONE
            initialsView.text = contact.initials
        }
    }
}