package com.amalip.data.entity

import org.ktorm.schema.Table
import org.ktorm.schema.double
import org.ktorm.schema.int

object UserCourseEntity : Table<Nothing>("user_course") {

    val id = int("id").primaryKey()
    val courseId = int("id_course")
    val userId = int("id_user")
    val grade1 = double("grade1")
    val grade2 = double("grade2")
    val grade3 = double("grade3")

}