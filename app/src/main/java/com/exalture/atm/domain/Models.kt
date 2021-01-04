package com.exalture.atm.domain

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class ExaltureProjects(
    val id: String,
    val logoImgSrcUrl: String,
    val detailsImgSrcUrl: String,
    val type: String,
    val title: String,
    val description: String
) : Parcelable {
    val isRental: Boolean
        get() = type == "rent"
}