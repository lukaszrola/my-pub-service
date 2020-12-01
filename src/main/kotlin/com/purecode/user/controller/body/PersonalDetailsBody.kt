package com.purecode.user.controller.body

import com.purecode.user.entity.details.RelationshipStatus
import com.purecode.user.entity.details.Sex
import io.micronaut.core.annotation.Introspected

@Introspected
class PersonalDetailsBody (
        val name: String,
        val sex: Sex,
        val dateOfBirth: DateBody,
        val relationshipStatus: RelationshipStatus)
