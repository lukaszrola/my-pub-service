package com.purecode.user.controller

import com.purecode.user.controller.body.UserResponseBody
import com.purecode.user.entity.*
import com.purecode.user.entity.alcohol.Alcohol
import com.purecode.user.entity.alcohol.AlcoholKind
import com.purecode.user.entity.contact.Contact
import com.purecode.user.entity.details.PersonalDetails
import com.purecode.user.entity.details.RelationshipStatus
import com.purecode.user.entity.details.Sex
import com.purecode.user.entity.preferences.*
import com.purecode.user.service.UserOperation
import com.purecode.user.service.UserService
import com.purecode.user.service.exception.UserNotFoundByEmailException
import io.kotest.core.spec.style.BehaviorSpec
import io.micronaut.http.HttpStatus
import io.micronaut.security.authentication.Authentication
import io.mockk.every
import io.mockk.mockk
import org.amshove.kluent.shouldBe
import org.amshove.kluent.shouldBeEqualTo
import java.time.LocalDate

class UserControllerTest : BehaviorSpec({
    given("user controller with user service") {
        val correctEmailAddress = EmailAddress("correct.email@wp.pl")
        val incorrectEmail = EmailAddress("incorrect.email@wp.pl")
        val user = buildUser()
        val userService: UserService = mockk()
        val userController = UserController(userService)

        every { userService.findUserByEmail(correctEmailAddress) } returns UserOperation.success(user)
        every { userService.findUserByEmail(incorrectEmail) } returns UserOperation.failure(
            UserNotFoundByEmailException(incorrectEmail.value)
        )

        `when`("user authenticated with correct email") {
            val authentication: Authentication = mockk()
            every { authentication.name } returns correctEmailAddress.value

            val result = userController.getUser(authentication)

            then("expected user with response code 200 was returned") {
                result.status shouldBe HttpStatus.OK
                result.body() shouldBeEqualTo UserResponseBody(user)
            }
        }

        `when`("user authenticate with incorrect email") {
            val authentication: Authentication= mockk()
            every { authentication.name } returns incorrectEmail.value

            val  result = userController.getUser(authentication)

            then("error message with code 404 was returned") {
                result.status shouldBe HttpStatus.NOT_FOUND
            }
        }

    }
})

fun buildUser() = User(
    id = UserId("1"),

    password = Password("somePassword"),
    contact = Contact(
        email = EmailAddress("correct.email@wp.pl"),
        phone = PhoneNumber("523 543 432")
    ),
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
    ),
    favoriteAlcohols = listOf(
        Alcohol(
            name = "Porter Bałtycki",
            brand = "Komes",
            kind = AlcoholKind.BEER
        )
    )
)