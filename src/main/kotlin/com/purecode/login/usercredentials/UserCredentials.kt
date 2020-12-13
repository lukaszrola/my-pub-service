package com.purecode.login.usercredentials

class UserCredentials(val userName: String, private val password: String) {
    fun checkPassword(password: String) =
        if (this.password == password) PasswordCheckResult.CORRECT_PASSWORD else PasswordCheckResult.INCORRECT_PASSWORD
}