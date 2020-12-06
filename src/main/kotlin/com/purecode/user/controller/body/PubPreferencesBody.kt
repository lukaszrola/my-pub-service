package com.purecode.user.controller.body

import com.purecode.user.entity.alcohol.AlcoholKind
import com.purecode.user.entity.preferences.*
import io.micronaut.core.annotation.Introspected

@Introspected
data class PubPreferencesBody(
    val alcoholKind: AlcoholKind,
    val musicKind: MusicKind,
    val pubSize: PubSize,
    val pubCongestion: PubCongestion,
    val smokingPermission: SmokingPermission
) {
    constructor(pubPreferences: PubPreferences) : this(
            alcoholKind = pubPreferences.alcoholKind,
            musicKind = pubPreferences.musicKind,
            pubSize = pubPreferences.pubSize,
            pubCongestion = pubPreferences.pubCongestion,
            smokingPermission = pubPreferences.smokingPermission
    )
}