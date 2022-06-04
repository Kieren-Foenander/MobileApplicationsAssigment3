package kieren.foenander.propertywatch

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import kieren.foenander.propertywatch.api.PropertyItem


class PropertyWatchrViewModel : ViewModel() {

    val propertyItemLiveData: LiveData<List<PropertyItem>>

    init {
        propertyItemLiveData = WatchrFetchr().fetchProperties()
    }
}
