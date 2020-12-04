package com.purecode.user.controller.error

import com.purecode.user.service.exception.UserNotFoundException
import io.micronaut.http.HttpResponse
import io.micronaut.http.hateoas.JsonError

class UserErrors{
    companion object {
        fun resolve(exception: Throwable): HttpResponse<JsonError> =
            if(exception is UserNotFoundException) {
                HttpResponse.notFound(
                        JsonError("user with email ${exception.email} not found")
                )
            } else {
                HttpResponse.serverError(
                        JsonError("unexpected error occurred")
                )
            }

    }
}