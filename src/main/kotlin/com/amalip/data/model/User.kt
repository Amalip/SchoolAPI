package com.amalip.data.model

import kotlinx.serialization.Serializable

@Serializable
data class User(

    val id: Int = 0,
    val name: String = "",
    val firstLastname: String = "",
    val secondLastname: String = "",
    val email: String = "",
    val picture: String = "",
    val enrollment: String = "",
    val password: String = "",
    val level: Int = 0

)
