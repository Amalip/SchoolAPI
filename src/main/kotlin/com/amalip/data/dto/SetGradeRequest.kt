package com.amalip.data.dto

import kotlinx.serialization.Serializable

@Serializable
data class SetGradeRequest(

    val userId: Int = 0,
    val courseId: Int = 0,
    val grade: Double = 0.0,
    val partial: Int = 0

)
