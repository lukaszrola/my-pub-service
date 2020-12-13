package com.purecode.login.refreshtoken

import io.micronaut.runtime.event.annotation.EventListener
import io.micronaut.security.authentication.UserDetails
import io.micronaut.security.errors.IssuingAnAccessTokenErrorCode
import io.micronaut.security.errors.OauthErrorResponseException
import io.micronaut.security.token.event.RefreshTokenGeneratedEvent
import io.micronaut.security.token.refresh.RefreshTokenPersistence
import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable
import io.reactivex.FlowableEmitter
import org.reactivestreams.Publisher
import java.util.*
import javax.inject.Singleton

@Singleton
class CustomRefreshTokenPersistence
    (private val refreshTokenRepository: RefreshTokenRepository) : RefreshTokenPersistence {
    @EventListener
    override fun persistToken(event: RefreshTokenGeneratedEvent) {
        if (event.refreshToken != null && event.userDetails != null && event.userDetails.username != null) {
            val payload = event.refreshToken
            refreshTokenRepository.save(event.userDetails.username, payload, false)
        }
    }

    override fun getUserDetails(refreshToken: String): Publisher<UserDetails> {
        return Flowable.create({ emitter: FlowableEmitter<UserDetails> ->
            val token = refreshTokenRepository.findByRefreshToken(refreshToken)
            token?.let {
                if (it.revoked) {
                    emitter.onError(
                        OauthErrorResponseException(
                            IssuingAnAccessTokenErrorCode.INVALID_GRANT, "refresh token revoked", null
                        )
                    )
                } else {
                    emitter.onNext(UserDetails(it.username, ArrayList()))
                    emitter.onComplete()
                }
            } ?: emitter.onError(
                OauthErrorResponseException(
                    IssuingAnAccessTokenErrorCode.INVALID_GRANT,
                    "refresh token not found",
                    null
                )
            )
        }, BackpressureStrategy.ERROR)
    }
}