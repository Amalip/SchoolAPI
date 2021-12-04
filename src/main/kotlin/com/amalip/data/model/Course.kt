package com.amalip.data.model

import kotlinx.serialization.Serializable

@Serializable
data class Course(

    val id: Int= 0,
    val name: String = "",
    val description: String = "",
    val picture: String = "",
    var scheduleList: MutableList<String> = mutableListOf(),
    var grades: MutableList<Int> = mutableListOf()

)
