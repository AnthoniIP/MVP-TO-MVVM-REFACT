package dominando.android.hotel.repository.room

import androidx.lifecycle.LiveData
import androidx.room.*
import dominando.android.hotel.model.Hotel
import dominando.android.hotel.repository.sqlite.COLUMN_ID
import dominando.android.hotel.repository.sqlite.COLUMN_NAME
import dominando.android.hotel.repository.sqlite.TABLE_HOTEL

/**
 *
 *  Author:     Anthoni Ipiranga
 *  Project:    hoteis
 *  Date:       03/03/2021
 */

@Dao
interface HotelDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(hotel: Hotel): Long

    @Update
    suspend fun update(hotel: Hotel): Int

    @Delete
    suspend fun delete(vararg hotels: Hotel): Int

    @Query("SELECT * FROM $TABLE_HOTEL WHERE $COLUMN_ID = :id")
    suspend fun hotelById(id: Long): LiveData<Hotel>

    @Query(
        """SELECT * FROM $TABLE_HOTEL
    WHERE $COLUMN_NAME LIKE :query ORDER BY $COLUMN_NAME"""
    )
    suspend fun search(query: String): LiveData<List<Hotel>>
}