package com.purecode.user.service

class UserOperation<out T> private constructor(private val operationResult: Any) {
    fun <R> fold(
        onSuccess: (value: T) -> R,
        onFailure: (exception: Throwable) -> R
    ): R {
       return if(operationResult is Throwable){
           onFailure(operationResult)
        }
        else {
            onSuccess(operationResult as T)
        }
    }

    companion object {
        fun <T : Any>success (result: T) : UserOperation<T> {
            return UserOperation(result)
        }

        fun <T>failure(exception: Throwable) :UserOperation<T> {
            return UserOperation(exception)
        }
    }
}