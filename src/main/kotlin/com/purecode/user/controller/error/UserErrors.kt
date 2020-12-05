package com.purecode.user.controller.error

import com.purecode.user.service.exception.UserNotFoundByEmailException
import com.purecode.user.service.exception.UserOperationException
import io.micronaut.http.HttpResponse
import io.micronaut.http.hateoas.JsonError

class UserErrors{
    companion object {
        fun resolve(exception: UserOperationException): HttpResponse<JsonError> =
            when (exception) {
                is UserNotFoundByEmailException -> HttpResponse.notFound(
                    JsonError("user with email ${exception.email} not found")
                )
            }
    }
}