package com.purecode.user.controller.body

import com.purecode.user.entity.alcohol.Alcohol
import com.purecode.user.entity.alcohol.AlcoholKind
import io.micronaut.core.annotation.Introspected

@Introspected
data class AlcoholBody(val name: String, val brand: String, val kind: AlcoholKind) {
    constructor(alcohol: Alcohol) : this(
        name = alcohol.name,
        brand = alcohol.brand,
        kind = alcohol.kind
    )
}