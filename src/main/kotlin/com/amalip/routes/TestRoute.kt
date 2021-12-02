package com.amalip.routes

import com.amalip.data.model.User
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*
import java.io.File

fun Route.testRoute() {

    get {
        //To get query params
        call.request.queryParameters["someQueryParam"]
        call.parameters["someQueryParam"]

        //Add info header
        call.response.headers.append("Server-Name", "First API in Ktor")

        //To respond
        call.respond(HttpStatusCode.OK, User(10, "Amalip", "amalip@gmail.com"))
    }

    post("/test") {
        //To get body request
        val body = call.receive<User>()
    }

    //Download files
    get("/downloadFile") {
        val file = File("path/fileName.png")

        call.response.header(
            HttpHeaders.ContentDisposition,
            ContentDisposition.Attachment.withParameter(
                ContentDisposition.Parameters.FileName,
                "downloadableImage.png"
            ).toString()
        )

        call.respondFile(file)

    }

    get("/openFile") {
        val file = File("path/fileName.png")

        call.response.header(
            HttpHeaders.ContentDisposition,
            ContentDisposition.Inline.withParameter(
                ContentDisposition.Parameters.FileName,
                "openImage.png"
            ).toString()
        )

        call.respondFile(file)

    }

}