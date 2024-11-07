package edu.matiasborra.demo03

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import edu.matiasborra.demo03.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private val TAG = MainActivity::class.java.simpleName

    private lateinit var fragmentList: FragmentList
    private lateinit var fragmentAdd: FragmentAdd

    companion object {
        private var fragmentShowed: String? = null
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putString("fragmentShowed", fragmentShowed)
        super.onSaveInstanceState(outState)
    }

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

        // Si no hay fragmento mostrado, carga el fragmento de lista
        if(savedInstanceState == null){
            loadFragment(fragmentList)
        } else {
            fragmentShowed = savedInstanceState.getString("fragmentShowed")
            when(fragmentShowed){
                fragmentList.javaClass.simpleName -> loadFragment(fragmentList)
                fragmentAdd.javaClass.simpleName -> loadFragment(fragmentAdd)
            }
        }
    }

    // Polimorfismo de Fragment
    private fun loadFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(binding.mFrameLayout.id, fragment)
            .commit()

        fragmentShowed = fragment.javaClass.simpleName
    }

    // Eventos de ciclo de vida cuando pulso lista abre vista de lista y cuando pulso añadir abre vista de añadir
    override fun onStart() {
        super.onStart()
        Log.i(TAG, "onStart")
        binding.mBottomNavView.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.op_list -> loadFragment(fragmentList)
                R.id.op_add -> loadFragment(fragmentAdd)
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