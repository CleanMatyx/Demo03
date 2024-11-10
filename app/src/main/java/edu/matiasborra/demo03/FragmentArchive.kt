package edu.matiasborra.demo03

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import edu.matiasborra.demo03.adapters.ItemsAdapter
import edu.matiasborra.demo03.databinding.ListFragmentBinding
import edu.matiasborra.edumatiasborrademo02.model.Items

/**
 * Fragmento para la lista de elementos archivados
 */
class FragmentArchive() : Fragment() {
    private val TAG = FragmentArchive::class.java.simpleName
    private lateinit var binding: ListFragmentBinding
    //view model compartido entre fragmentos
    private val sharedViewModel: MainViewModel by activityViewModels()
    private val adapter = ItemsAdapter()

    /**
     * Creación de la vista del fragmento
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return vista del fragmento
     */
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        Log.i(TAG, "onCreateView")
        Log.d(TAG, "onCreateView: ${Items.items.size}")
        binding = ListFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    /**
     * Creación de la vista del fragmento
     * @param view
     * @param savedInstanceState
     */
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        Log.i(TAG, "onViewCreated")
        super.onViewCreated(view, savedInstanceState)
        adapter.submitList(sharedViewModel.fetchArchivedItems())        //asi cargo los elementos archivados
        binding.mRecycled.layoutManager = LinearLayoutManager(context)
        binding.mRecycled.adapter = adapter
    }
}