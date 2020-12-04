package com.purecode.user.service

import com.purecode.user.entity.EmailAddress
import com.purecode.user.entity.User
import com.purecode.user.repository.UserRepository
import com.purecode.user.service.exception.UserNotFoundException
import javax.inject.Singleton

@Singleton
class UserService(private val userRepository: UserRepository) {
    fun findUserByEmail(emailAddress: EmailAddress): Result<User> {
        return userRepository.findUserByEmail(emailAddress)?.let { Result.success(it) }
                ?: Result.failure(UserNotFoundException("$emailAddress"))
    }
}