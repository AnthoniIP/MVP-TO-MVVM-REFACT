package dominando.android.hotel.repository.room

import androidx.lifecycle.LiveData
import dominando.android.hotel.model.Hotel
import dominando.android.hotel.repository.HotelRepository

/**
 *
 *  Author:     Anthoni Ipiranga
 *  Project:    hoteis
 *  Date:       03/03/2021
 */

class RoomRepository(
    database: HotelDatabase
) : HotelRepository {
    private val hotelDao = database.hotelDao()
    override suspend fun save(hotel: Hotel) {
        if (hotel.id == 0L) {
            val id = hotelDao.insert(hotel)
            hotel.id = id
        } else {
            hotelDao.update(hotel)
        }
    }

    override suspend fun remove(vararg hotels: Hotel) {
        hotelDao.delete(*hotels)
    }

    override suspend fun hotelById(id: Long): LiveData<Hotel> {
        return hotelDao.hotelById(id)
    }

    override suspend fun search(term: String): LiveData<List<Hotel>> {
        return hotelDao.search(term)
    }

}