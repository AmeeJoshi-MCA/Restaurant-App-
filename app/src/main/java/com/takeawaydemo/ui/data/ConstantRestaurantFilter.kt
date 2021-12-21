package com.takeawaydemo.ui.data

object ConstantRestaurantFilter {
    var FilterOptionList = mutableListOf<FilterOption>(
        FilterOption(
            filterName = "Open",
            select=false
            ),
        FilterOption(
            filterName = "Rating",
            select=false
        ),
        FilterOption(
            filterName = "Popular",
            select=false
        ),
        FilterOption(
            filterName = "Distance",
            select=false
        ),
        FilterOption(
            filterName = "Cost",
            select=false
        ),
    )
}