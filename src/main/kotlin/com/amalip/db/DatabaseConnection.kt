package com.amalip.db

import org.ktorm.database.Database

object DatabaseConnection {

    //TODO Set correct password

    val database = Database.connect(
            url = "jdbc:mysql://localhost:3306/school",
            driver = "com.mysql.cj.jdbc.Driver",
            user = "root",
            password = "*Amalip1996Mysql."
        )

}