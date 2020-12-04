package com.purecode.user.service

import com.purecode.user.entity.EmailAddress
import com.purecode.user.entity.User
import com.purecode.user.repository.UserRepository
import com.purecode.user.service.exception.UserNotFoundException
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

    given("User service with repository containing one user") {
        val correctEmail = EmailAddress("some.email@wp.pl")
        val incorrectEmail = EmailAddress("incorrect.email@wp.pl")
        every { userRepository.findUserByEmail(correctEmail) } returns user
        every { userRepository.findUserByEmail(incorrectEmail) } returns null

        `when`("Looking user by correct email address") {
            val result = userService.findUserByEmail(correctEmail)

            then("User was found") {
                result.isSuccess shouldBe true
                result.getOrNull() shouldBeEqualTo user
            }
        }

        `when`("Looking user by incorrect email") {
            val result = userService.findUserByEmail(incorrectEmail)

            then("User was not found") {
                result.isFailure shouldBe true
                result.onFailure {
                    it shouldBeInstanceOf UserNotFoundException::class
                    it as UserNotFoundException
                    it.email shouldBeEqualTo incorrectEmail.value
                }
            }
        }
    }
})