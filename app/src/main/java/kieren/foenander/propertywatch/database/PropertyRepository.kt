package kieren.foenander.propertywatch.database

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.*
import java.util.concurrent.Executors

@Dao
interface PropertyDao {

    @Query("SELECT * FROM property")
    fun getProperties(): LiveData<List<Property>>

    @Query ("SELECT * FROM property WHERE id=(:id)")
    fun getProperties(id: String): LiveData<Property?>

    @Update
    fun updateProperty(property: Property)

    @Insert
    fun addProperty(property: Property)

    @Insert
    fun addProperties(property: List <Property>)
}

class PropertyRepository private constructor(context: Context) {

    private val database : PropertyDatabase = Room.databaseBuilder(
        context.applicationContext, PropertyDatabase::class.java, "property-database"
    ).build()

    private val propertyDao = database.propertyDao()
    private val executor = Executors.newSingleThreadExecutor()

    fun getProperties(): LiveData<List<Property>> = propertyDao.getProperties()

    fun addProperties(properties: List<Property>) {
        executor.execute {
            propertyDao.addProperties(properties)
        }
    }

    fun updateProperty(property: Property) {
        executor.execute {
            propertyDao.updateProperty(property)
        }
    }

    companion object
    {
        private var INSTANCE: PropertyRepository? = null
        //var testDataLoaded = false

        fun initialize(context: Context) {
            if (INSTANCE == null) {
                INSTANCE = PropertyRepository(context)
            }
        }

        fun get(): PropertyRepository {
            return INSTANCE ?:
            throw IllegalStateException("DogRepository not initialized")
        }
/*
        fun loadTestData()
        {
            if (testDataLoaded)
                return

            var properties: ArrayList<Property> = ArrayList()
            properties.add(Property(address = "22 yeet street", price = 500000, phone = "0477020777", lat = 16.928893272553427, lon = -16.928893272553427))
            properties.add(Property(address = "22 yeet street", price = 500000, phone = "0477020777", lat = 16.928893272553427, lon = -16.928893272553427))
            properties.add(Property(address = "22 yeet street", price = 500000, phone = "0477020777", lat = 16.928893272553427, lon = -16.928893272553427))
            properties.add(Property(address = "22 yeet street", price = 500000, phone = "0477020777", lat = 16.928893272553427, lon = -16.928893272553427))
            properties.add(Property(address = "22 yeet street", price = 500000, phone = "0477020777", lat = 16.928893272553427, lon = -16.928893272553427))
            properties.add(Property(address = "22 yeet street", price = 500000, phone = "0477020777", lat = 16.928893272553427, lon = -16.928893272553427))


            INSTANCE?.addProperties(properties)

            testDataLoaded = true
        }
*/

        fun loadData() {
            val propertyArray: ArrayList<Property> = ArrayList()
            propertyArray.add(Property(
                address="149-153 Bunda Street, Cairns",
                price=200000,
                phone="0403404111",
                lat=-16.928893272553427, lon=145.77021565990813))
            propertyArray.add(Property(
                address="197 Draper Street, Cairns",
                price=350000,
                phone="0403404222",
                lat=-16.928893272553427, lon=145.77021565990813))
            propertyArray.add(Property(
                address="198 Grafton Street, Cairns",
                price=800000,
                phone="0403404333",
                lat=-16.916479904985984, lon=145.76987256094102))
            propertyArray.add(Property(
                address="3 Cominos Place, Cairns",
                price=550000,
                phone="0403404444",
                lat=-16.922791, lon=145.745504))
            propertyArray.add(Property(
                address="6 McGuigan Street, Earlville",
                price=400000,
                phone="0403404555",
                lat=-16.945571, lon=145.741207))
            propertyArray.add(Property(
                address="6-8 Quigley Street, Manunda",
                price=455000,
                phone="0403404666",
                lat=-16.929026, lon=145.762279))
            INSTANCE?.addProperties(propertyArray)
        }
    }

}