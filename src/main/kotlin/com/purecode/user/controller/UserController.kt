package com.purecode.user.controller

import com.purecode.user.controller.body.UserResponseBody
import com.purecode.user.controller.error.UserErrors
import com.purecode.user.entity.EmailAddress
import com.purecode.user.service.UserService
import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.security.annotation.Secured
import io.micronaut.security.authentication.Authentication
import io.micronaut.security.rules.SecurityRule

@Secured(SecurityRule.IS_AUTHENTICATED)
@Controller("users")
class UserController(private val userService: UserService) {

    @Get
    fun getUser(authentication: Authentication): HttpResponse<*> {
        return userService
            .findUserByEmail(EmailAddress(authentication.name))
            .fold(
                onSuccess = { HttpResponse.ok(UserResponseBody(it)) },
                onFailure = { UserErrors.resolve(it) }
            )
    }
}