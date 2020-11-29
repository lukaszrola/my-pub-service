package com.purecode.user.entity.preferences

import io.micronaut.core.annotation.Introspected

@Introspected
data class Alcohol(val name: String, val brand: String, val kind: AlcoholKind)