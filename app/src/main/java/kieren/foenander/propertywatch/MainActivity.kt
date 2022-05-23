package kieren.foenander.propertywatch

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider

class MainActivity : AppCompatActivity() {

    private lateinit var mPropertyViewModel: PropertyViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mPropertyViewModel = ViewModelProvider(this).get(PropertyViewModel::class.java)


        mPropertyViewModel.selectedProperty.observe(this){
            loadFragment(PropertyListFragment.newInstance())

        }

        if(savedInstanceState == null){
            loadFragment(PropertyListFragment.newInstance())
        }
    }

    fun loadFragment(fragment: Fragment){
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.container, fragment)
            .commit()
    }

    override fun onBackPressed(){
        val currentFragment: Fragment? =
            supportFragmentManager.findFragmentById(R.id.container)

        // add if statement here if need to go back from other pages for back button
    }
}