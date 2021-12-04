package com.amalip.data.entity

import org.ktorm.schema.Table
import org.ktorm.schema.int

object UserCourseEntity : Table<Nothing>("user_course") {

    val id = int("id").primaryKey()
    val courseId = int("id_course")
    val userId = int("id_user")
    val grade1 = int("grade1")
    val grade2 = int("grade2")
    val grade3 = int("grade3")

}