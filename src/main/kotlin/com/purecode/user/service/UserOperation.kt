package com.purecode.user.service

import com.purecode.user.entity.UserOperationResult
import com.purecode.user.service.exception.UserOperationException

class UserOperation<out T : UserOperationResult> private constructor(private val operationResult: UserOperationResult) {
    fun <R> fold(
        onSuccess: (value: T) -> R,
        onFailure: (exception: UserOperationException) -> R
    ): R {
       return if(operationResult is UserOperationException){
           onFailure(operationResult)
        }
        else {
           @Suppress("UNCHECKED_CAST")
           onSuccess(operationResult as T)
       }
    }

    companion object {
        fun <T : UserOperationResult>success (result: T) : UserOperation<T> {
            return UserOperation(result)
        }

        fun <T : UserOperationResult>failure(exception: UserOperationException) :UserOperation<T> {
            return UserOperation(exception)
        }
    }
}