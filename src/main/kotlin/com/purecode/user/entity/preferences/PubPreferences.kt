package com.purecode.user.entity.preferences

import com.purecode.user.entity.alcohol.AlcoholKind

class PubPreferences(
    val alcoholKind: AlcoholKind,
    val musicKind: MusicKind,
    val pubSize: PubSize,
    val pubCongestion: PubCongestion,
    val smokingPermission: SmokingPermission
)