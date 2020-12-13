package com.purecode.login.refreshtoken

import java.time.Instant
import javax.inject.Singleton
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

@Singleton
class RefreshTokenRepository {
    private val refreshTokens: MutableList<RefreshTokenEntity> = mutableListOf()
    private var idPool = 1L

    fun save(
        username: @NotBlank String,
        refreshToken: @NotBlank String,
        revoked: @NotNull Boolean
    ): RefreshTokenEntity {
        val refreshTokenEntity = RefreshTokenEntity(
            id = idPool++,
            username = username,
            refreshToken = refreshToken,
            revoked = revoked,
            dateCreated = Instant.now()
        )

        refreshTokens.add(refreshTokenEntity)

        return refreshTokenEntity
    }

    fun findByRefreshToken(refreshToken: @NotBlank String): RefreshTokenEntity? {
        return refreshTokens.firstOrNull { it.refreshToken == refreshToken }
    }

    fun revokeForUsers(username: @NotBlank String, revoked: @NotNull Boolean): Long {
        val userTokens = refreshTokens.filter { it.username == username }
        userTokens.forEach { it.revoked = revoked }

        return userTokens.size.toLong()
    }
}