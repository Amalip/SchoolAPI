package com.amalip.data.entity

import org.ktorm.schema.Table
import org.ktorm.schema.int
import org.ktorm.schema.varchar

object CourseEntity : Table<Nothing>("course") {

    val id = int("id").primaryKey()
    val name = varchar("name")
    val description = varchar("description")
    val picture = varchar("picture")

}