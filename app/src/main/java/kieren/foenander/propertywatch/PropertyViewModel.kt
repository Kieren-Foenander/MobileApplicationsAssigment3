package kieren.foenander.propertywatch

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kieren.foenander.propertywatch.database.Property

class PropertyViewModel: ViewModel() {
    val selectedProperty = MutableLiveData<Property>()

}