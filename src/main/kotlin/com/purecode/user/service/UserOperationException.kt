package com.purecode.user.service.exception

import com.purecode.user.entity.UserOperationResult

sealed class UserOperationException() : RuntimeException(), UserOperationResult

class UserNotFoundByEmailException(val email: String) : UserOperationException()