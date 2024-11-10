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

/**
 * Actividad principal de la aplicación
 * Carga de fragmentos y eventos de ciclo de vida
 */
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private val TAG = MainActivity::class.java.simpleName

    // Fragments declarados
    private lateinit var fragmentList: FragmentList
    private lateinit var fragmentAdd: FragmentAdd
    private lateinit var fragmentArchive: FragmentArchive       //añado fragment de archivados

    private val mainViewModel: MainViewModel by viewModels()

    /**
     * Creación de la vista de la actividad
     * @param savedInstanceState
     * Carga de fragmento de lista si no hay ninguno cargado
     */
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

    /**
     * Carga de fragmento
     * @param fragment
     * Carga el fragmento pasado por parámetro
     * Actualiza el fragmento mostrado en el view model
     * Fragmento mostrado por defecto es el de lista
     * Fragmento mostrado se actualiza al pulsar en el menú de navegación
     * Fragmento mostrado se actualiza al rotar la pantalla
     */
    private fun loadFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(binding.mFrameLayout.id, fragment)
            .commit()

        mainViewModel.setFragmentShowed(fragment.javaClass.simpleName)
    }

    /**
     * Evento de inicio de la actividad
     * Carga de fragmentos y eventos de selección del menú de navegación
     * Actualiza el fragmento mostrado al pulsar en el menú de navegación
     * @return true
     */
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
}