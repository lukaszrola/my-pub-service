package com.purecode.user.service

import com.purecode.user.entity.EmailAddress
import com.purecode.user.entity.User
import com.purecode.user.repository.UserRepository
import com.purecode.user.service.exception.UserNotFoundException
import javax.inject.Singleton

@Singleton
open class UserService(private val userRepository: UserRepository) {
    open fun findUserByEmail(emailAddress: EmailAddress): UserOperation<User> {
        return userRepository.findUserByEmail(emailAddress)?.let { UserOperation.success(it) }
                ?: UserOperation.failure(UserNotFoundException("$emailAddress"))
    }
}