package kieren.foenander.propertywatch

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kieren.foenander.propertywatch.database.PropertyListViewModel
import kieren.foenander.propertywatch.database.PropertyRepository

class PropertyListFragment: Fragment() {

    companion object{
        fun newInstance() = PropertyListFragment()
    }

    private lateinit var mPropertyListViewModel: PropertyListViewModel

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)

        val context = activity as ViewModelStoreOwner
        mPropertyListViewModel = ViewModelProvider(context).get(PropertyListViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?): View? {

        val recyclerView = inflater.inflate(R.layout.fragment_list, container, false) as RecyclerView
        recyclerView.layoutManager = LinearLayoutManager(context)

        mPropertyListViewModel.propertyList.observe(
            viewLifecycleOwner, Observer { propertyList ->
                if (propertyList.isEmpty()){
                    PropertyRepository.loadData()
                    return@Observer
                }
                recyclerView.adapter = PropertyAdapter(propertyList)
            })
        return recyclerView
    }
}
