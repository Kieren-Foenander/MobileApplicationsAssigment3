package kieren.foenander.propertywatch

import android.security.identity.AccessControlProfileId

data class Property(
    var id: String,
    var address: String,
    var price: Int,
    var phone: String,
    var lat: Double,
    var long: Double
)
