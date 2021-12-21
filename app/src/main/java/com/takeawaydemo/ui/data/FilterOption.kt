package com.takeawaydemo.ui.data


import com.iebayirli.recyclerviewwithdatabinding.base.ListAdapterItem
import java.io.Serializable

data class FilterOption (
    val filterName: String,
    var select: Boolean = false,
    override val id: Long = 0
) : ListAdapterItem,Serializable