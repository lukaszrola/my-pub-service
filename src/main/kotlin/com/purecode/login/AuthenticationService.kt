package com.purecode.login

import com.purecode.login.usercredentials.PasswordCheckResult
import com.purecode.login.usercredentials.repository.UserCredentialsRepository
import io.micronaut.http.HttpRequest
import io.micronaut.security.authentication.*
import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable
import io.reactivex.FlowableEmitter
import org.reactivestreams.Publisher
import java.util.*
import javax.inject.Singleton

@Singleton
class AuthenticationService(private val userCredentialsRepository: UserCredentialsRepository) : AuthenticationProvider {

    override fun authenticate(
        httpRequest: HttpRequest<*>?,
        authenticationRequest: AuthenticationRequest<*, *>
    ): Publisher<AuthenticationResponse> =
        Flowable.create({ emitter: FlowableEmitter<AuthenticationResponse> ->
            if (passwordIsCorrect(authenticationRequest)) {
                emitter.onNext(UserDetails(authenticationRequest.identity as String, ArrayList()))
                emitter.onComplete()
            } else {
                emitter.onError(AuthenticationException("invalid credentials"))
            }
        }, BackpressureStrategy.ERROR)

    private fun passwordIsCorrect(authenticationRequest: AuthenticationRequest<*, *>) =
        userCredentialsRepository.findByUserName(authenticationRequest.identity.toString())
            ?.checkPassword(authenticationRequest.secret.toString()) == PasswordCheckResult.CORRECT_PASSWORD

}