package kieren.foenander.propertywatch.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [ Property:: class ], version=1, exportSchema=false)
abstract class PropertyDatabase: RoomDatabase(){

    abstract fun propertyDao(): PropertyDao
}