package com.example.mugiwaracrew

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Crew(
    val name: String,
    val position: String,
    val from: String,
    val description: String,
    val photo: String,
    val link: String
) : Parcelable