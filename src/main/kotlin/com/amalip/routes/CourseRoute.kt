package com.amalip.routes

import com.amalip.data.entity.CourseEntity
import com.amalip.data.entity.CourseScheduleEntity
import com.amalip.data.entity.UserCourseEntity
import com.amalip.data.entity.UserEntity
import com.amalip.data.enums.UserLevel
import com.amalip.data.model.Course
import com.amalip.data.model.Error
import com.amalip.data.model.User
import com.amalip.db.DatabaseConnection
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.response.*
import io.ktor.routing.*
import org.ktorm.dsl.*
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.LocalDateTime

fun Route.courseRoute() {

    val db = DatabaseConnection.database

    route("/course") {

        get("/byUser") {

            val courseEntity = CourseEntity.apply { aliased("co") }
            val userCourseEntity = UserCourseEntity.apply { aliased("uc") }

            val userId = call.parameters["userId"]?.toInt() ?: 0

            val result =
                db.from(courseEntity).leftJoin(userCourseEntity, on = courseEntity.id eq userCourseEntity.courseId)
                    .select().where { userCourseEntity.userId eq userId }.map { toCourse(it) }

            if (result.isNotEmpty())
                call.respond(HttpStatusCode.OK, result)
            else call.respond(HttpStatusCode.NotFound, Error(HttpStatusCode.NotFound.value, "This user has no courses"))
        }

        get("/byId") {

            val courseId = call.parameters["courseId"]?.toInt() ?: 0

            val result =
                db.from(CourseEntity)
                    .leftJoin(CourseScheduleEntity, on = CourseEntity.id eq CourseScheduleEntity.courseId).select()
                    .where { CourseEntity.id eq courseId }

            val firstList = mutableListOf<String>()
            val course = result.map {
                firstList.add(formatSchedule(it))
                toCourse(it)
            }[0].apply {
                scheduleList = firstList
            }


            if (course.id > 0)
                call.respond(HttpStatusCode.OK, course)
            else call.respond(HttpStatusCode.NotFound, Error(HttpStatusCode.NotFound.value, "Course not found"))

        }


    }

}


private fun toCourse(row: QueryRowSet) = Course(
    id = row[CourseEntity.id] ?: 0,
    name = row[CourseEntity.name] ?: "",
    description = row[CourseEntity.description] ?: "",
    picture = row[CourseEntity.picture] ?: "",
    scheduleList = mutableListOf("")
)

private fun formatSchedule(row: QueryRowSet): String {

    val day = row[CourseScheduleEntity.day] ?: 0
    val hour = row[CourseScheduleEntity.hour]

    return "${DayOfWeek.of(day).name}, $hour"

}