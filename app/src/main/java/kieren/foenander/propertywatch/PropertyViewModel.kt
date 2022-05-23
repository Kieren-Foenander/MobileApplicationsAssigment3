package kieren.foenander.propertywatch

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class PropertyViewModel: ViewModel() {
    val selectedProperty = MutableLiveData<Property>()

}