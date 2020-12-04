package com.purecode.user.service.exception

import java.lang.RuntimeException

class UserNotFoundException(val email: String) : RuntimeException() {
}