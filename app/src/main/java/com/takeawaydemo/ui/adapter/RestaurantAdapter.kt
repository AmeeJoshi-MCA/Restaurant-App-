package com.takeawaydemo.ui.adapter


import com.iebayirli.recyclerviewwithdatabinding.base.BaseAdapter
import com.takeawaydemo.R
import com.takeawaydemo.databinding.ItemRestaurantBinding
import com.takeawaydemo.ui.data.Restaurant

class RestaurantAdapter(
    private val list: List<Restaurant>,
    private val restaurantListener: RestaurantListener
) : BaseAdapter<ItemRestaurantBinding, Restaurant>(list) {

    override val layoutId: Int = R.layout.item_restaurant

    override fun bind(binding: ItemRestaurantBinding, item: Restaurant) {
        binding.apply {
            restaurant = item
            listener = restaurantListener
            executePendingBindings()
        }
    }
}

interface RestaurantListener {
    fun onRestaurantClicked(restaurant: Restaurant)
    fun onFavouriteClicked(restaurant: Restaurant)
}