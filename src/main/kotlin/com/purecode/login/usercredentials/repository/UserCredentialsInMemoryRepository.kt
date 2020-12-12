package com.purecode.login.usercredentials.repository

import com.purecode.login.usercredentials.UserCredentials
import javax.inject.Singleton

@Singleton
internal class UserCredentialsInMemoryRepository : UserCredentialsRepository {
    private val userList: List<UserCredentials> = listOf(
        UserCredentials(
            userName = "lukasz.rola@wp.pl",
            password = "password"
        )
    )

    override fun findByUserName(userName: String): UserCredentials? = userList.firstOrNull { it.userName == userName }

}