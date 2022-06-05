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
import kieren.foenander.propertywatch.adapters.PropertyAdapter
import kieren.foenander.propertywatch.database.Property
import kieren.foenander.propertywatch.database.PropertyListViewModel

class PropertyListFragment: Fragment() {

    companion object{
        fun newInstance() = PropertyListFragment()
    }

    private lateinit var mPropertyListViewModel: PropertyListViewModel
    private lateinit var mPropertyWatchrViewModel: PropertyWatchrViewModel
    private var mPropertyArray: ArrayList<Property> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)

        val context = activity as ViewModelStoreOwner
        mPropertyListViewModel = ViewModelProvider(context).get(PropertyListViewModel::class.java)
        mPropertyWatchrViewModel = ViewModelProvider(context).get(PropertyWatchrViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?): View? {

        val recyclerView = inflater.inflate(R.layout.fragment_list, container, false) as RecyclerView
        recyclerView.layoutManager = LinearLayoutManager(context)

        mPropertyWatchrViewModel.propertyItemLiveData.observe(
            viewLifecycleOwner, Observer { propertyItems ->
                for (item in propertyItems){
                    mPropertyArray.add(Property(item.id, item.address, item.price, item.phone, item.lat, item.lon))
                }
                recyclerView.adapter = PropertyAdapter(mPropertyArray)
            }
        )
        return recyclerView
    }
}
