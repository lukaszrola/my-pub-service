package com.purecode.user.controller.body

import com.purecode.user.entity.preferences.*
import io.micronaut.core.annotation.Introspected

@Introspected
class PubPreferencesBody (
        val alcoholKind: AlcoholKind,
        val musicKind: MusicKind,
        val pubSize: PubSize,
        val pubCongestion: PubCongestion,
        val smokingPermission: SmokingPermission
)