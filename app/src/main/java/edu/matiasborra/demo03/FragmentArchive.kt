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

class FragmentArchive() : Fragment() {
    private val TAG = FragmentArchive::class.java.simpleName
    private lateinit var binding: ListFragmentBinding
    //view model compartido entre fragmentos
    private val sharedViewModel: MainViewModel by activityViewModels()
    private val adapter = ItemsAdapter()


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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        Log.i(TAG, "onViewCreated")
        super.onViewCreated(view, savedInstanceState)
        adapter.submitList(sharedViewModel.fetchArchivedItems())        //asi cargo los elementos archivados
        binding.mRecycled.layoutManager = LinearLayoutManager(context)
        binding.mRecycled.adapter = adapter
    }

    override fun onResume() {
        super.onResume()
        Log.i(TAG, "onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.i(TAG, "onPause")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i(TAG, "onDestroy")
    }


}