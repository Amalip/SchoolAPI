package com.amalip.plugins

import com.amalip.routes.userRoute
import io.ktor.routing.*
import io.ktor.application.*

fun Application.configureRouting() {

    routing {
        userRoute()
    }
}
