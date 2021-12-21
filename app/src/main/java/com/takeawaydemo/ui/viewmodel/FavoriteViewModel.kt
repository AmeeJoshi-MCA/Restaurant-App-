package com.takeawaydemo.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.takeawaydemo.ui.adapter.FavoriteRestaurantListener
import com.takeawaydemo.ui.data.ConstRestaurantData
import com.takeawaydemo.ui.data.Event
import com.takeawaydemo.ui.data.Restaurant
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class FavoriteViewModel : ViewModel(), FavoriteRestaurantListener {

    private val _restaurantList = MutableLiveData<List<Restaurant>>()
    val favoriteRestaurantList: LiveData<List<Restaurant>> = _restaurantList

    private val _showProgressBar = MutableLiveData(false)
    val showToast = MutableLiveData<Event<Restaurant>>()

    init {
        submitMovieList()
    }

    private fun submitMovieList() {

        viewModelScope.launch {
            fetchFavoriteRestaurant()
                .onStart {
                    _showProgressBar.postValue(true)
                }.catch { err ->
                    _showProgressBar.postValue(false)
                }
                .collect { list ->
                    _showProgressBar.postValue(false)
                    _restaurantList.value = list
                }
        }
    }

    private fun fetchFavoriteRestaurant() = flow<List<Restaurant>> {
        delay(100)
        emit(ConstRestaurantData.restaurantData.filter { it.favourite==true})
    }.flowOn(Dispatchers.IO)


    override fun onRestaurantClicked(restaurant: Restaurant) {
        showToast.value = Event(restaurant)
    }
}