package com.takeawaydemo.ui.data

import androidx.annotation.DrawableRes
import com.iebayirli.recyclerviewwithdatabinding.base.ListAdapterItem
import java.io.Serializable

data class Restaurant(
    val name: String,
    val sortingValues: SortingValues,
    val status: String,
    val address: String,
    @DrawableRes val image: Int,
    var favourite: Boolean = false,
    override val id: Long = 0
) : ListAdapterItem, Serializable