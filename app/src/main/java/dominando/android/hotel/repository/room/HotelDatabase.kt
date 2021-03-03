package dominando.android.hotel.repository.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import dominando.android.hotel.model.Hotel
import dominando.android.hotel.repository.sqlite.DATABASE_NAME
import dominando.android.hotel.repository.sqlite.DATABASE_VERSION

/**
 *
 *  Author:     Anthoni Ipiranga
 *  Project:    hoteis
 *  Date:       03/03/2021
 */

@Database(entities = [Hotel::class],version = DATABASE_VERSION)
abstract class HotelDatabase : RoomDatabase() {

    abstract fun hotelDao(): HotelDao

    companion object {
        private var instance: HotelDatabase? = null

        fun getDataBase(context: Context): HotelDatabase {
            if(instance == null) {
                instance = Room.databaseBuilder(
                    context.applicationContext,
                    HotelDatabase::class.java,
                    DATABASE_NAME
                ).build()
            }
            return instance as HotelDatabase
        }
        fun destroyInstance() {
            instance = null
        }
    }

}