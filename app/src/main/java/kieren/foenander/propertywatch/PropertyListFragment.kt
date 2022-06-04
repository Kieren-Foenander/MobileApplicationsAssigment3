package kieren.foenander.propertywatch

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kieren.foenander.propertywatch.api.PropertyItem
import kieren.foenander.propertywatch.database.Property
import kieren.foenander.propertywatch.database.PropertyListViewModel
import kieren.foenander.propertywatch.database.PropertyRepository

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

        val apiProperty = mPropertyWatchrViewModel.propertyItemLiveData.value
        if (apiProperty != null) {
            Log.d("yeet", apiProperty.get(0).address.toString())
        }

        mPropertyWatchrViewModel.propertyItemLiveData.observe(
            viewLifecycleOwner, Observer { propertyItemLiveData->
                if (apiProperty != null) {
                    Log.d("yeet", apiProperty.get(0).address.toString())
                }
            }
        )

        if (apiProperty != null){
            for(i in 0 until (apiProperty.size -1)){
                val id = apiProperty.get(i).id
                val address = apiProperty.get(i).address
                val price = apiProperty.get(i).price
                val phone = apiProperty.get(i).phone
                val lat = apiProperty.get(i).lat
                val lon = apiProperty.get(i).lon

                mPropertyArray.add(Property(id, address, price, phone, lat, lon))
            }
        }


        mPropertyListViewModel.propertyList.observe(
            viewLifecycleOwner, Observer { propertyList ->
                if (propertyList.isEmpty()){
                    PropertyRepository.loadData(mPropertyArray)
                    return@Observer
                }
                recyclerView.adapter = PropertyAdapter(propertyList)
            })
        return recyclerView
    }
}
