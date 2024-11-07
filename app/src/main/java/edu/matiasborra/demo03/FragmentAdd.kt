package edu.matiasborra.demo03

import android.content.Context.INPUT_METHOD_SERVICE
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import edu.matiasborra.demo03.databinding.AddFragmentBinding
import edu.matiasborra.edumatiasborrademo02.model.Items

class FragmentAdd : Fragment() {
    private val TAG = FragmentAdd::class.java.simpleName
    private lateinit var binding: AddFragmentBinding
    //view model compartido entre fragmentos
    private val sharedViewModel: MainViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        Log.i(TAG, "onCreateView")
        binding = AddFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        Log.i(TAG, "onViewCreated")
        super.onViewCreated(view, savedInstanceState)

        binding.btnSave.setOnClickListener {
            binding.tilTitle.error = null
            binding.tilDesc.error = null

            val title = binding.tietTitle.text.toString().trim()
            val description = binding.tietDesc.text.toString().trim()

            if(title.isEmpty()){
                binding.tilTitle.error = getString(R.string.error_title)
                return@setOnClickListener
            }

            if(description.isEmpty()){
                binding.tilDesc.error = getString(R.string.error_desc)
                return@setOnClickListener
            }

//            val item = Items(title = title, description = description)
//            Items.items.add(item)
//            Log.d(TAG, "onViewCreated: $item")
            sharedViewModel.addItem(Items(title = title, description = description))

            binding.tietTitle.text?.clear()
            binding.tietDesc.text?.clear()

            //esconder el teclado

            val imm = requireActivity().getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view.windowToken, 0)
        }
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