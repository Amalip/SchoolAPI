package com.amalip.routes

import com.amalip.data.entity.UserCourseEntity
import com.amalip.data.enums.UserLevel
import com.amalip.data.entity.UserEntity
import com.amalip.data.model.Error
import com.amalip.data.model.User
import com.amalip.db.DatabaseConnection
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*
import org.ktorm.dsl.*

fun Route.userRoute() {

    val db = DatabaseConnection.database

    route("/user") {

        get("/all") {
            val users = db.from(UserEntity).select().map { toUser(it) }

            call.respond(HttpStatusCode.OK, users)

        }

        post("/login") {
            val userRequest = call.receive<User>()

            val result = db.from(UserEntity).select().where {
                (UserEntity.enrollment eq userRequest.enrollment) and
                        (UserEntity.password eq userRequest.password) and
                        (UserEntity.level eq userRequest.level)
            }.map { toUser(it) }

            if (result.isNotEmpty() && result[0].id > 0)
                call.respond(HttpStatusCode.OK, result[0])
            else
                call.respond(
                    HttpStatusCode.BadRequest,
                    Error(HttpStatusCode.BadRequest.value, "User not found, check credentials")
                )
        }

        put {
            val userToEdit = call.receive<User>()

            val result = db.update(UserEntity) {
                set(it.name, userToEdit.name)
                set(it.firstLastname, userToEdit.firstLastname)
                set(it.secondLastname, userToEdit.secondLastname)
                set(it.email, userToEdit.email)
                set(it.picture, userToEdit.picture)
                set(it.password, userToEdit.password)
                where {
                    it.id eq userToEdit.id
                }
            }

            if (result != 0)
                call.respond(HttpStatusCode.OK)
            else call.respond(
                HttpStatusCode.NotModified,
                Error(HttpStatusCode.NotModified.value, "Error modifying user")
            )

        }

        get("/byCourse") {
            val courseId = call.parameters["courseId"]?.toInt() ?: 0

            val result =
                db.from(UserEntity).leftJoin(UserCourseEntity, on = UserEntity.id eq UserCourseEntity.userId).select()
                    .where { UserCourseEntity.courseId eq courseId }.map { toUser(it) }

            call.respond(HttpStatusCode.OK, result)
        }

    }

}

private fun toUser(row: QueryRowSet) = User(
    id = row[UserEntity.id] ?: 0,
    name = row[UserEntity.name] ?: "",
    firstLastname = row[UserEntity.firstLastname] ?: "",
    secondLastname = row[UserEntity.secondLastname] ?: "",
    email = row[UserEntity.email] ?: "",
    picture = row[UserEntity.picture] ?: "",
    enrollment = row[UserEntity.enrollment] ?: "",
    password = row[UserEntity.password] ?: "",
    level = row[UserEntity.level] ?: UserLevel.STUDENT.code
)