package com.example.aston_intensive_6

import android.content.Context
import android.content.res.Configuration
import android.os.Bundle
import android.os.Parcelable
import android.view.*
import androidx.appcompat.widget.SearchView
import androidx.core.view.MenuItemCompat
import androidx.fragment.app.*
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.aston_intensive_6.data.Contact
import com.example.aston_intensive_6.data.ContactsRepo
import com.example.aston_intensive_6.databinding.ContactsListBinding
import java.io.Serializable
import java.util.*
import kotlin.reflect.typeOf


const val TO_DESC = "TO_DESC"
const val FROM_DESC = "FROM_DESC"
const val CONTACT = "CONTACT"

private const val RECYCLER_VIEW_STATE = "RECYCLER_VIEW_STATE"
private const val REPO_STATE = "REPO_STATE"
private const val FILTERED_LIST_STATE = "FILTERED_LIST_STATE"
private const val LAST_EDITED_INDEX_STATE = "LAST_EDITED_INDEX_STATE"

class ContactsListFragment : Fragment(R.layout.contacts_list) {

    private var _binding: ContactsListBinding? = null
    private val binding get() = _binding!!
    private lateinit var adapter: CustomAdapter

    private var repo = ContactsRepo()
    private var displayableList = repo.contactsList
    private var filteredList = mutableListOf<Contact>()

    private var lastEdited: Contact? = null
    private var lastEditedIndex: Int? = null

    private lateinit var layoutManager: RecyclerView.LayoutManager
    private var recyclerState: Parcelable? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (savedInstanceState != null) {
            filteredList = savedInstanceState.getSerializable(FILTERED_LIST_STATE) as MutableList<Contact>
            if(filteredList.isNotEmpty())
                displayableList = filteredList
            recyclerState = savedInstanceState.getParcelable(RECYCLER_VIEW_STATE)
            repo = savedInstanceState.getSerializable(REPO_STATE) as ContactsRepo
            lastEditedIndex = savedInstanceState.getInt(LAST_EDITED_INDEX_STATE)
        }

        layoutManager = LinearLayoutManager(requireContext())

        setFragmentResultListener(FROM_DESC) { _, bundle ->
            lastEditedIndex?.let {
                updateContact(bundle, it)
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = ContactsListBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (recyclerState != null)
            binding.recycler.layoutManager?.onRestoreInstanceState(recyclerState)

        binding.searchBar.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                if (!query.isNullOrBlank()) {
                    filteredList.clear()
                    binding.searchBar.clearFocus()
                    repo.contactsList.forEach {
                        if (it.name.contains(query, true))
                            filteredList.add(it)
                    }
                    displayableList = filteredList
                    adapter.notifyDataSetChanged()
                    initRecycler()
                }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })

        initRecycler()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun updateContact(bundle: Bundle, index: Int) {
        repo.updateContact(index, bundle.getSerializable(RETURNED_CONTACT) as Contact)

        //TODO DiffUtil
        adapter.notifyItemChanged(index)
    }

    private fun initRecycler() {
        layoutManager = LinearLayoutManager(requireContext())
        binding.recycler.layoutManager = layoutManager
        adapter = CustomAdapter(
            displayableList,
            { contact, index ->
                openDescription(contact)
                lastEditedIndex = index
            },
            {
                repo.deleteContact(it)
            }
        )
        binding.recycler.adapter = adapter
    }

    private fun openDescription(contact: Contact) {
        val bundle = Bundle()
        lastEdited = contact

        bundle.putSerializable(CONTACT, contact as Serializable)

        setFragmentResult(TO_DESC, bundle)

        parentFragmentManager.commit {
            setCustomAnimations(
                R.anim.slide_in,
                R.anim.fade_out,
                R.anim.fade_in,
                R.anim.slide_out
            )
            if (isTablet(requireContext())) {
                replace<ContactDescription>(R.id.details_fragment_container)
            } else {
                replace<ContactDescription>(R.id.master_fragment_container)
                addToBackStack(null)
            }
        }
    }

    private fun isTablet(context: Context): Boolean {
        return ((context.resources.configuration.screenLayout
                and Configuration.SCREENLAYOUT_SIZE_MASK)
                >= Configuration.SCREENLAYOUT_SIZE_LARGE)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        lastEditedIndex?.let {
            outState.putInt(LAST_EDITED_INDEX_STATE, it)
        }
        outState.putSerializable(FILTERED_LIST_STATE, filteredList as Serializable)
        outState.putSerializable(REPO_STATE, repo as Serializable)
        outState.putParcelable(RECYCLER_VIEW_STATE, layoutManager.onSaveInstanceState())
    }
}