package dominando.android.hotel.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import dominando.android.hotel.model.Hotel
import dominando.android.hotel.repository.HotelRepository

/**
 *
 *  Author:     Anthoni Ipiranga
 *  Project:    hoteis
 *  Date:       03/03/2021
 */

class HotelDetailsViewModel(
    private val repository: HotelRepository
) : ViewModel() {

    fun loadHotelDetails(id: Long): LiveData<Hotel> {
        return liveData {
            val data = repository.hotelById(id)
            data.value?.let { emit(it) }
        }


    }
}