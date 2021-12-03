package com.amalip.data.entity

import org.ktorm.schema.Table
import org.ktorm.schema.int
import org.ktorm.schema.time

object CourseScheduleEntity : Table<Nothing>("course_schedule") {

    val id = int("id").primaryKey()
    val courseId = int("id_course")
    val day = int("day")
    val hour = time("hour")

}