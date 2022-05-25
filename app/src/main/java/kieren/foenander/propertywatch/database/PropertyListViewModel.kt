package kieren.foenander.propertywatch.database

import androidx.lifecycle.ViewModel

class PropertyListViewModel: ViewModel() {
    private val propertyRepository = PropertyRepository.get()
    val propertyList = propertyRepository.getProperties()

}