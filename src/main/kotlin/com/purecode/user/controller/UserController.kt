package com.purecode.user.controller

import com.purecode.user.controller.body.UserResponseBody
import com.purecode.user.controller.error.UserErrors
import com.purecode.user.entity.EmailAddress
import com.purecode.user.service.UserService
import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.QueryValue
import io.micronaut.security.annotation.Secured
import io.micronaut.security.rules.SecurityRule

@Secured(SecurityRule.IS_AUTHENTICATED) // <1>
@Controller("users")
class UserController(private val userService: UserService) {

    @Get
    fun getUser(@QueryValue email: String): HttpResponse<*> = userService
            .findUserByEmail(EmailAddress(email))
            .fold(
                    onSuccess = { HttpResponse.ok(UserResponseBody(it)) },
                    onFailure = { UserErrors.resolve(it) }
            )
}