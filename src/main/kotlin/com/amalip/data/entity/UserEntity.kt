package com.amalip.data.entity

import org.ktorm.schema.Table
import org.ktorm.schema.int
import org.ktorm.schema.varchar

object UserEntity : Table<Nothing>("user") {

    val id = int("id").primaryKey()
    val name = varchar("name")
    val firstLastname = varchar("first_lastname")
    val secondLastname = varchar("second_lastname")
    val email = varchar("email")
    val picture = varchar("picture")
    val enrollment = varchar("enrollment")
    val password = varchar("password")
    val level = int("level")

}