package dominando.android.hotel.repository

import androidx.lifecycle.LiveData
import dominando.android.hotel.model.Hotel

interface HotelRepository {
    suspend fun save(hotel: Hotel)
    suspend fun remove(vararg hotels: Hotel)
    suspend fun hotelById(id: Long): LiveData<Hotel>
    suspend fun search(term: String): LiveData<List<Hotel>>
}