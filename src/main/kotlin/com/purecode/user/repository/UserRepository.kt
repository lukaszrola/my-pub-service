package com.purecode.user.repository

import com.purecode.user.entity.EmailAddress
import com.purecode.user.entity.User

interface UserRepository {
    fun addNewUser(user: User): User
    fun findUserByEmail(emailAddress: EmailAddress) : User?
}