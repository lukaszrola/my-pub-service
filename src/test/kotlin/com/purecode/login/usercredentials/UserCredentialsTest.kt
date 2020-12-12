package com.purecode.login.usercredentials

import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import org.amshove.kluent.shouldBeEqualTo

class UserCredentialsTest : BehaviorSpec({
    given("user credentials") {
        val correctPassword = "password"
        val userCredentials = UserCredentials(userName = "username", password = correctPassword)

        `when`("provided correct password") {
            val passwordCheckResult = userCredentials.checkPassword(correctPassword)

            then("result is correct password") {
                passwordCheckResult shouldBe PasswordCheckResult.CORRECT_PASSWORD
            }
        }

        `when`("provided incorrect password") {
            val passwordCheckResult = userCredentials.checkPassword("incorrectPassword")

            then("result is incorrect password") {
                passwordCheckResult shouldBe PasswordCheckResult.INCORRECT_PASSWORD
            }
        }
    }
})
