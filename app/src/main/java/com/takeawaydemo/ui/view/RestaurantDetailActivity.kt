package com.takeawaydemo.ui.view

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.takeawaydemo.R
import com.takeawaydemo.databinding.ActivityRestaurantDetailBinding
import com.takeawaydemo.ui.data.Restaurant

class RestaurantDetailActivity : AppCompatActivity() {

    private lateinit var activityRestaurantDetailBinding: ActivityRestaurantDetailBinding
    private lateinit var restaurant: Restaurant
    private var context: Context? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityRestaurantDetailBinding = DataBindingUtil.setContentView(this, R.layout.activity_restaurant_detail)
        context=this

        if (getSupportActionBar() != null) {
            getSupportActionBar()!!.hide();
        }

        val bundle :Bundle ?=intent.extras
        if (bundle!=null) {
            restaurant = bundle!!.getSerializable("restaurant") as Restaurant
        }

        activityRestaurantDetailBinding.imgResDetailBack.setOnClickListener {
            finish()
        }

        setRestaurantDetailData()
    }

    private fun setRestaurantDetailData() {
        activityRestaurantDetailBinding.apply {
           textViewName.text=restaurant.name
           txtViewDilCost.text="Delivery $ "+restaurant.sortingValues.deliveryCosts
           txtViewAvgCost.text="Average $ "+restaurant.sortingValues.averageProductPrice
           txtViewMinCost.text="Min Cost $ "+restaurant.sortingValues.minCost
           textViewAddress.text=restaurant.address

           if(restaurant.sortingValues.ratingAverage <= 0) {
               ratingBar.visibility=View.GONE
           }else{
               ratingBar.visibility=View.VISIBLE
               ratingBar.rating = restaurant.sortingValues.ratingAverage.toFloat()
           }

            if (restaurant.favourite) {
                imageViewFavourite.setImageResource(R.drawable.ic_fav_fill)
            } else {
                imageViewFavourite.setImageResource(R.drawable.ic_fav_outline)
            }

        }
    }
}