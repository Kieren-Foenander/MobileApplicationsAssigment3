package kieren.foenander.propertywatch

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import androidx.recyclerview.widget.RecyclerView
import kieren.foenander.propertywatch.database.Property
import kieren.foenander.propertywatch.database.PropertyListViewModel

class PropertyAdapter(var properties: List<Property>): RecyclerView.Adapter<PropertyAdapter.PropertyViewHolder>() {
    private lateinit var mPropertyListViewModel: PropertyListViewModel

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PropertyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)

        val viewModelStoreOwner = parent.context as ViewModelStoreOwner

        mPropertyListViewModel = ViewModelProvider(viewModelStoreOwner).get(PropertyListViewModel::class.java)

        return PropertyViewHolder(view)

    }

    override fun getItemCount() = properties.size

    override fun onBindViewHolder(propertyHolder: PropertyViewHolder, position: Int) {
        val property = properties[position]
        propertyHolder.bind(property)
    }

    inner class PropertyViewHolder(val view: View): RecyclerView.ViewHolder(view), View.OnClickListener{
        lateinit var property: Property

        init {
            itemView.setOnClickListener(this)

        }

        override fun onClick(v: View){
            Log.d("PropertyAdapter", property.address + " selected")
            //mPropertyListViewModel.selectedProperty.value = property
        }

        fun bind(property: Property){
            this.property = property

            val addressView: TextView = view.findViewById(R.id.address)
            val priceView: TextView = view.findViewById(R.id.price)
            val phoneView: TextView = view.findViewById(R.id.phone)

            addressView.text = property.address
            priceView.text = property.price.toString()
            phoneView.text = property.phone



        }

    }
}