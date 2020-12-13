package com.purecode.user.controller.body

import io.micronaut.core.annotation.Introspected

@Introspected
data class DateBody(
        val year: Int,
        val month: Int,
        val day: Int
)