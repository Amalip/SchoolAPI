package com.amalip.data.entity

import org.ktorm.schema.Table
import org.ktorm.schema.int

object CourseScheduleEntity : Table<Nothing>("course") {

    val id = int("id").primaryKey()
    val courseId = int("id_course")
    val day = int("day")
    val hour = int("hour")

}