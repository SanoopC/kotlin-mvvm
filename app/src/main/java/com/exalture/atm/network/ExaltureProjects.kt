package com.exalture.atm.network

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ExaltureProjects(
    val id: String,
    // used to map img_src from the JSON to imgSrcUrl in our class
    @Json(name = "logo_img_src") val logoImgSrcUrl: String,
    @Json(name = "details_img_src") val detailsImgSrcUrl: String,
    val type: String,
    val title: String,
    val description: String
) : Parcelable {
    val isRental: Boolean
        get() = type == "rent"
}