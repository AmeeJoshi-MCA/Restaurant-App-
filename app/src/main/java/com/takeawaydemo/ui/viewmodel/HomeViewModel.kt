package com.takeawaydemo.ui.viewmodel

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.takeawaydemo.ui.adapter.RestaurantFilterListener
import com.takeawaydemo.ui.adapter.RestaurantListener
import com.takeawaydemo.ui.data.*
import com.takeawaydemo.ui.view.RestaurantDetailActivity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel(), RestaurantListener, RestaurantFilterListener {

    private val _restaurantList = MutableLiveData<List<Restaurant>>()
    val restaurantList: LiveData<List<Restaurant>> = _restaurantList

    private val _filterOptionList = MutableLiveData<List<FilterOption>>()
    val filterOption: LiveData<List<FilterOption>> = _filterOptionList

    private val _showProgressBar = MutableLiveData(false)
    val showProgressBar: LiveData<Boolean> = _showProgressBar

    val showToast = MutableLiveData<Event<Restaurant>>()

    init {
        submitRestaurantList()
        submitRestaurantFilterOption()
    }

    private fun submitRestaurantList() {
        viewModelScope.launch {
            fetchRestaurant()
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

    private fun submitRestaurantFilterOption() {
        viewModelScope.launch {
            fetchRestaurantFilterOption()
                .onStart {
                    _showProgressBar.postValue(true)
                }.catch { err ->
                    _showProgressBar.postValue(false)
                }
                .collect { list ->
                    _showProgressBar.postValue(false)
                    _filterOptionList.value = list
                }

        }
    }

    private fun submitRestaurantSelectedFilter(filterOption: FilterOption) {
        viewModelScope.launch {
            fetchRestaurantForFilter(filterOption)
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

    private fun fetchRestaurant() = flow<List<Restaurant>> {
        delay(100)
        emit(ConstRestaurantData.restaurantData)
    }.flowOn(Dispatchers.IO)


    private fun fetchRestaurantFilterOption() = flow<List<FilterOption>> {
        delay(100)
        emit(ConstantRestaurantFilter.FilterOptionList)
    }.flowOn(Dispatchers.IO)

    private fun fetchRestaurantForFilter(filterOption: FilterOption) = flow<List<Restaurant>> {
        delay(100)
        if(filterOption.filterName == "Open") {
            emit(ConstRestaurantData.restaurantData.filter {
                it.status == filterOption.filterName
            })
        }else if(filterOption.filterName == "Rating"){
            emit(ConstRestaurantData.restaurantData.sortedByDescending { it.sortingValues.ratingAverage }
            )
        }else if(filterOption.filterName == "Popular"){
            emit(ConstRestaurantData.restaurantData.sortedByDescending { it.sortingValues.popularity }
            )
        }else if(filterOption.filterName == "Distance"){
            emit(ConstRestaurantData.restaurantData.sortedBy { it.sortingValues.distance }
            )
        }else if(filterOption.filterName == "Cost"){
            emit(ConstRestaurantData.restaurantData.sortedBy { it.sortingValues.minCost }
            )
        }
    }.flowOn(Dispatchers.IO)


    override fun onFavouriteClicked(restaurant: Restaurant) {
        val ind = ConstRestaurantData.restaurantData.indexOf(restaurant)
        ConstRestaurantData.restaurantData[ind].favourite =
            !ConstRestaurantData.restaurantData[ind].favourite
        submitRestaurantList()
    }

    override fun onRestaurantClicked(restaurant: Restaurant) {
        showToast.value = Event(restaurant)
    }

    override fun onFilterClicked(filterOption: FilterOption) {

        if(filterOption.select == true){
            filterOption.select=false
            submitRestaurantFilterOption()
            submitRestaurantList()
        }else {
            for (item in ConstantRestaurantFilter.FilterOptionList) {
                item.select = false
            }
            val ind = ConstantRestaurantFilter.FilterOptionList.indexOf(filterOption)
            ConstantRestaurantFilter.FilterOptionList[ind].select =
                !ConstantRestaurantFilter.FilterOptionList[ind].select
            submitRestaurantFilterOption()
            submitRestaurantSelectedFilter(filterOption)
        }
    }

}