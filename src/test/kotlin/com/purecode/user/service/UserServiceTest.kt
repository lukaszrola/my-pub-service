package com.purecode.user.service

import com.purecode.user.entity.EmailAddress
import com.purecode.user.entity.User
import com.purecode.user.repository.UserRepository
import com.purecode.user.service.exception.UserNotFoundException
import io.kotest.assertions.fail
import io.kotest.core.spec.style.BehaviorSpec
import io.mockk.every
import io.mockk.mockk
import org.amshove.kluent.shouldBe
import org.amshove.kluent.shouldBeEqualTo
import org.amshove.kluent.shouldBeInstanceOf

internal class UserServiceTest : BehaviorSpec({
    val userRepository: UserRepository = mockk()
    val user: User = mockk()
    val userService = UserService(userRepository)

    given("user service with repository containing one user") {
        val correctEmail = EmailAddress("some.email@wp.pl")
        val incorrectEmail = EmailAddress("incorrect.email@wp.pl")
        every { userRepository.findUserByEmail(correctEmail) } returns user
        every { userRepository.findUserByEmail(incorrectEmail) } returns null

        `when`("looking user by correct email address") {
            val result = userService.findUserByEmail(correctEmail)

            then("user was found") {
                result.fold(
                    onSuccess = {
                        it shouldBeEqualTo user
                    },
                    onFailure = {
                        fail("user was not found")
                    }
                )
            }
        }

        `when`("looking user by incorrect email") {
            val result = userService.findUserByEmail(incorrectEmail)

            then("user was not found") {
                result.fold(
                    onSuccess = { fail("user shouldn't be returned") },
                    onFailure = {
                        it shouldBeInstanceOf UserNotFoundException::class
                    }
                )
            }
        }
    }
})