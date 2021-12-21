package com.takeawaydemo.ui.adapter


import com.iebayirli.recyclerviewwithdatabinding.base.BaseAdapter
import com.takeawaydemo.R
import com.takeawaydemo.databinding.ItemFavoriteRestaurantBinding
import com.takeawaydemo.ui.data.Restaurant

class FavoriteRestaurantAdapter(
    private val list: List<Restaurant>,
    private val favoriteRestaurantListener: FavoriteRestaurantListener
) : BaseAdapter<ItemFavoriteRestaurantBinding, Restaurant>(list) {

    override val layoutId: Int = R.layout.item_favorite_restaurant

    override fun bind(binding: ItemFavoriteRestaurantBinding, item: Restaurant) {
        binding.apply {
            restaurant = item
            listener = favoriteRestaurantListener
            executePendingBindings()
        }
    }
}

interface FavoriteRestaurantListener {
    fun onRestaurantClicked(movie: Restaurant)
}