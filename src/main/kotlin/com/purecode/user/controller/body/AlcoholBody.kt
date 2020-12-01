package com.purecode.user.controller.body

import com.purecode.user.entity.preferences.AlcoholKind
import io.micronaut.core.annotation.Introspected

@Introspected
data class AlcoholBody(val name: String, val brand: String, val kind: AlcoholKind)