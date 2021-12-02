package com.amalip.data.model

import kotlinx.serialization.Serializable

@Serializable
data class Error(

    val error: Int,
    val message: String

)
