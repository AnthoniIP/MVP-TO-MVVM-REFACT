package dominando.android.hotel.form

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import dominando.android.hotel.model.Hotel
import dominando.android.hotel.repository.HotelRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 *
 *  Author:     Anthoni Ipiranga
 *  Project:    hoteis
 *  Date:       03/03/2021
 */

class HotelFormViewModel(
    private val repository: HotelRepository
) : ViewModel() {
    private val validator by lazy { HotelValidator() }

    fun loadHotel(id: Long): LiveData<Hotel> {
        return liveData {
            val data = repository.hotelById(id)
            data.value?.let { emit(it) }
        }
    }

    fun saveHotel(hotel: Hotel): Boolean {
        return validator.validate(hotel)
            .also { validated ->
                if (validated) {
                    viewModelScope.launch(Dispatchers.IO) {
                        repository.save(hotel)
                    }
                }
            }
    }
}