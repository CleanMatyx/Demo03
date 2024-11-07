package edu.matiasborra.demo03

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import edu.matiasborra.demo03.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private val TAG = MainActivity::class.java.simpleName

    // Fragments declarados
    private lateinit var fragmentList: FragmentList
    private lateinit var fragmentAdd: FragmentAdd
    private lateinit var fragmentArchive: FragmentArchive       //añado fragment de archivados

    private val mainViewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        Log.i(TAG, "onCreate")
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, 0)
            insets
        }
        fragmentList = FragmentList()
        fragmentAdd = FragmentAdd()
        fragmentArchive = FragmentArchive()

        // Si no hay fragmento mostrado, carga el fragmento de lista
        if(mainViewModel.fragmentShowed == null){
            loadFragment(fragmentList)
        } else {
            when(mainViewModel.fragmentShowed){
                fragmentList.javaClass.simpleName -> loadFragment(fragmentList)
                fragmentAdd.javaClass.simpleName -> loadFragment(fragmentAdd)
                fragmentArchive.javaClass.simpleName -> loadFragment(fragmentArchive)
            }
        }
    }

    // Polimorfismo de Fragment
    private fun loadFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(binding.mFrameLayout.id, fragment)
            .commit()

        mainViewModel.setFragmentShowed(fragment.javaClass.simpleName)
    }

    // Eventos de ciclo de vida cuando pulso lista abre vista de lista y cuando pulso añadir abre vista de añadir
    override fun onStart() {
        super.onStart()
        Log.i(TAG, "onStart")
        binding.mBottomNavView.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.op_list -> loadFragment(fragmentList)
                R.id.op_add -> loadFragment(fragmentAdd)
                R.id.op_archive -> loadFragment(fragmentArchive)
            }
            true
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