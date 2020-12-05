package com.purecode.user.controller

import com.purecode.user.controller.body.UserResponseBody
import com.purecode.user.entity.*
import com.purecode.user.entity.details.PersonalDetails
import com.purecode.user.entity.details.RelationshipStatus
import com.purecode.user.entity.details.Sex
import com.purecode.user.entity.preferences.*
import com.purecode.user.service.UserOperation
import com.purecode.user.service.UserService
import com.purecode.user.service.exception.UserNotFoundByEmailException
import io.kotest.core.spec.style.BehaviorSpec
import io.micronaut.http.HttpRequest
import io.micronaut.http.HttpStatus
import io.micronaut.http.MutableHttpRequest
import io.micronaut.http.client.HttpClient
import io.micronaut.http.client.annotation.Client
import io.micronaut.http.client.exceptions.HttpClientResponseException
import io.micronaut.test.annotation.MockBean
import io.micronaut.test.extensions.kotest.MicronautKotestExtension.getMock
import io.micronaut.test.extensions.kotest.annotation.MicronautTest
import io.mockk.every
import io.mockk.mockk
import org.amshove.kluent.shouldBeEqualTo
import java.time.LocalDate


@MicronautTest
class UserControllerTest(@Client("/") private val client: HttpClient, private val userService: UserService) :
    BehaviorSpec({
        given("user controller for service with one user") {
            `when`("request with incorrect email address was performed") {
                val userServiceMock = getMock(userService)
                setUpMock(userServiceMock)
                val request = HttpRequest.GET<UserResponseBody>("/users?email=$incorrectEmail")

                val response = getResponse(client, request)

                then("response user not found is returned") {
                    response.status shouldBeEqualTo HttpStatus.NOT_FOUND
                }
            }

            `when`("request with correct email was performed") {
                val userServiceMock = getMock(userService)
                setUpMock(userServiceMock)
                val request = HttpRequest.GET<UserResponseBody>("/users?email=$correctEmail")
                val response = getResponse(client, request)

                then("expectedUser is returned") {
                    response.status shouldBeEqualTo HttpStatus.OK
                }
            }

        }

    }) {


    @MockBean(UserService::class)
    fun userService(): UserService {
        return mockk()
    }
}

const val correctEmail = "correct.email@wp.pl"
const val incorrectEmail = "incorrect.email@wp.pl"
val user: User = User(
    id = UserId("1"),
    email = EmailAddress("correct.email@wp.pl"),
    password = Password("somePassword"),
    phone = PhoneNumber("523 543 432"),
    personalDetails = PersonalDetails(
        name = "Some name",
        sex = Sex.MALE,
        dateOfBirth = LocalDate.of(1995, 3, 8),
        relationshipStatus = RelationshipStatus.MARRIED
    ),
    pubPreferences = PubPreferences(
        alcoholKind = AlcoholKind.BEER,
        musicKind = MusicKind.ROCK,
        pubSize = PubSize.MEDIUM,
        pubCongestion = PubCongestion.LOW,
        smokingPermission = SmokingPermission.NO_SMOKING
    )
)

private fun getResponse(client: HttpClient, request: MutableHttpRequest<UserResponseBody>) = try {
    client.toBlocking().exchange(request, UserResponseBody::class.java)
} catch (e: HttpClientResponseException) {
    e.response
}

fun setUpMock(userServiceMock: UserService) {
    every { userServiceMock.findUserByEmail(EmailAddress(incorrectEmail)) } returns UserOperation.failure(
        UserNotFoundByEmailException(incorrectEmail)
    )
    every { userServiceMock.findUserByEmail(EmailAddress(correctEmail)) } returns UserOperation.success(user)
}