package com.amalip.routes

import com.amalip.data.dto.SetGradeRequest
import com.amalip.data.entity.CourseEntity
import com.amalip.data.entity.CourseScheduleEntity
import com.amalip.data.entity.UserCourseEntity
import com.amalip.data.entity.UserEntity
import com.amalip.data.model.Course
import com.amalip.data.model.Error
import com.amalip.data.model.User
import com.amalip.db.DatabaseConnection
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*
import org.ktorm.dsl.*
import java.time.DayOfWeek

fun Route.courseRoute() {

    val db = DatabaseConnection.database

    route("/course") {

        get("/byUser") {
            val userId = call.parameters["userId"]?.toInt() ?: 0

            val result =
                db.from(CourseEntity)
                    .leftJoin(UserCourseEntity, on = CourseEntity.id eq UserCourseEntity.courseId)
                    .leftJoin(CourseScheduleEntity, on = CourseEntity.id eq CourseScheduleEntity.courseId)
                    .select().where { UserCourseEntity.userId eq userId }

            val courses = getCourses(result)

            if (courses.isNotEmpty())
                call.respond(HttpStatusCode.OK, courses)
            else call.respond(HttpStatusCode.NotFound, Error(HttpStatusCode.NotFound.value, "This user has no courses"))
        }

        get("/byId") {

            val courseId = call.parameters["courseId"]?.toInt() ?: 0
            val userId = call.parameters["userId"]?.toInt() ?: 0

            val result =
                db.from(CourseEntity)
                    .leftJoin(UserCourseEntity, on = CourseEntity.id eq UserCourseEntity.courseId)
                    .leftJoin(CourseScheduleEntity, on = CourseEntity.id eq CourseScheduleEntity.courseId).select()
                    .where { (CourseEntity.id eq courseId) and (UserCourseEntity.userId eq userId) }

            val course = getCourses(result)[0]

            if (course.id > 0)
                call.respond(HttpStatusCode.OK, course)
            else call.respond(HttpStatusCode.NotFound, Error(HttpStatusCode.NotFound.value, "Course not found"))
        }

        put {
            val gradeRequest = call.receive<SetGradeRequest>()

            val result = db.update(UserCourseEntity) {
                when (gradeRequest.partial) {
                    1 -> set(it.grade1, gradeRequest.grade)
                    2 -> set(it.grade2, gradeRequest.grade)
                    3 -> set(it.grade3, gradeRequest.grade)
                    else -> {}
                }
                where {
                    (it.userId eq gradeRequest.userId) and (it.courseId eq gradeRequest.courseId)
                }
            }

            if (result != 0)
                call.respond(HttpStatusCode.OK)
            else call.respond(
                HttpStatusCode.NotModified,
                Error(HttpStatusCode.NotModified.value, "Error modifying grade")
            )
        }

    }

}

private fun getCourses(result: Query): List<Course> {
    val courses = mutableListOf<Course>()
    result.forEach { row ->
        val course = toCourse(row).apply {
            scheduleList.add(formatSchedule(row))
            grades.add(row[UserCourseEntity.grade1] ?: 0)
            grades.add(row[UserCourseEntity.grade2] ?: 0)
            grades.add(row[UserCourseEntity.grade3] ?: 0)
        }
        courses.add(course)
    }

    val groupedCourses = courses.groupBy { it.id }

    val finalCourses = mutableListOf<Course>()
    groupedCourses.forEach { (key, value) ->
        val course = value[0]
        value.forEachIndexed { index, indexedCourse ->
            if (index == 0) return@forEachIndexed
            course.scheduleList.add(indexedCourse.scheduleList[0])
        }

        finalCourses.add(course)
    }

    return finalCourses.toList()
}

private fun toCourse(row: QueryRowSet) = Course(
    id = row[CourseEntity.id] ?: 0,
    name = row[CourseEntity.name] ?: "",
    description = row[CourseEntity.description] ?: "",
    picture = row[CourseEntity.picture] ?: "",
    scheduleList = mutableListOf(),
    grades = mutableListOf()
)

private fun formatSchedule(row: QueryRowSet): String {

    val day = row[CourseScheduleEntity.day] ?: 1
    val hour = row[CourseScheduleEntity.hour]

    return "${DayOfWeek.of(day).name}, $hour"

}