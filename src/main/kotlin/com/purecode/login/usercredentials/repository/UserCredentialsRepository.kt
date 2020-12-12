package com.purecode.login.usercredentials.repository

import com.purecode.login.usercredentials.UserCredentials

interface UserCredentialsRepository {
    fun findByUserName(userName : String) : UserCredentials?
}