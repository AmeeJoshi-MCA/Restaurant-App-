package com.takeawaydemo.ui.adapter


import com.iebayirli.recyclerviewwithdatabinding.base.BaseAdapter
import com.takeawaydemo.R
import com.takeawaydemo.databinding.ItemFilterBinding
import com.takeawaydemo.ui.data.FilterOption
import com.takeawaydemo.ui.data.Restaurant

class RestaurantFilterAdapter(
    private val list: List<FilterOption>,
    private val resFilterListener: RestaurantFilterListener
) : BaseAdapter<ItemFilterBinding, FilterOption>(list) {

    override val layoutId: Int = R.layout.item_filter

    override fun bind(binding: ItemFilterBinding, item: FilterOption) {
        binding.apply {
            filterOption = item
            restaurantFilterListener = resFilterListener
            executePendingBindings()
        }
    }
}

interface RestaurantFilterListener {
    fun onFilterClicked(filterOption: FilterOption)
}