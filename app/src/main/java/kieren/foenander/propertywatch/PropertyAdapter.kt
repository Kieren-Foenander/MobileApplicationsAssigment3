package kieren.foenander.propertywatch

import android.content.Intent
import android.content.Intent.EXTRA_INTENT
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
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
        lateinit var mproperty: Property
        private var v = view
        private val emailButton = view.findViewById(R.id.button) as Button


        init {
            itemView.setOnClickListener(this)
            emailButton.setOnClickListener(this)

        }

        override fun onClick(v: View){

            if(v?.id == R.id.button){
                when(v?.id){
                    R.id.button->{
                        sendEmail()
                    }
                }
            } else {
                Log.d("PropertyAdapter", mproperty.address + " selected")
                //init map function here

                val intent = Intent(v.context, MapsActivity::class.java)
                intent.putExtra(EXTRA_INTENT , mproperty)

                v.context.startActivity(intent)
            }
        }

        fun bind(property: Property){
            this.mproperty = property

            val addressView: TextView = view.findViewById(R.id.address)
            val priceView: TextView = view.findViewById(R.id.price)
            val phoneView: TextView = view.findViewById(R.id.phone)

            addressView.text = property.address
            priceView.text = property.price.toString()
            phoneView.text = property.phone

        }

        fun sendEmail(){
            var emailSubject = v.context.resources.getString(R.string.email_subject)
            var emailMessage = v.context.resources.getString(R.string.email_message, mproperty.address, mproperty.price.toString())

            val intent = Intent(Intent.ACTION_SEND)

            intent.putExtra(Intent.EXTRA_SUBJECT, emailSubject)
            intent.putExtra(Intent.EXTRA_TEXT, emailMessage)

            intent.type = "text/plain"

            v.context.startActivity(Intent.createChooser(intent, "Send using:"))

        }
    }
}