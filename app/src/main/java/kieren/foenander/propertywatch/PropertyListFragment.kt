package kieren.foenander.propertywatch

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class PropertyListFragment: Fragment() {

    companion object{
        fun newInstance() = PropertyListFragment()
    }

    private val mPropertyTestList: ArrayList <Property> = ArrayList()
    private lateinit var mPropertyViewModel: PropertyViewModel

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)

        mPropertyTestList.add(Property("1", "22 yeet stree", 400000, "0477020777", 16.928893272553427, -16.928893272553427))
        mPropertyTestList.add(Property("2", "22 yeet stree", 400000, "0477020777", 16.928893272553427, -16.928893272553427))
        mPropertyTestList.add(Property("3", "22 yeet stree", 400000, "0477020777", 16.928893272553427, -16.928893272553427))
        mPropertyTestList.add(Property("4", "22 yeet stree", 400000, "0477020777", 16.928893272553427, -16.928893272553427))

        val context = activity as ViewModelStoreOwner
        mPropertyViewModel = ViewModelProvider(context).get(PropertyViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?): View? {

        val recyclerView = inflater.inflate(R.layout.fragment_list, container, false) as RecyclerView
        recyclerView.layoutManager = LinearLayoutManager(context)

        recyclerView.adapter = PropertyAdapter(mPropertyTestList)

        return recyclerView
    }
}
