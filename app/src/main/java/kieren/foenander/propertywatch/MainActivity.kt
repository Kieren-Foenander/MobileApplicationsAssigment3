package kieren.foenander.propertywatch

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import kieren.foenander.propertywatch.database.PropertyListViewModel
import kieren.foenander.propertywatch.database.PropertyRepository

class MainActivity : AppCompatActivity() {

    private lateinit var mPropertyListViewModel: PropertyListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        PropertyRepository.initialize(this)

        mPropertyListViewModel = ViewModelProvider(this).get(PropertyListViewModel::class.java)

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