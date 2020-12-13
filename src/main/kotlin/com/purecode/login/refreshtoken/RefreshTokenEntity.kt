package com.purecode.login.refreshtoken

import java.time.Instant

class RefreshTokenEntity(
    val id: Long,
    val username: String,
    val refreshToken: String,
    var revoked: Boolean,
    val dateCreated: Instant
)