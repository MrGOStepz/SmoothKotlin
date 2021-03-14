package com.smooth.pos.model.location

data class LocationTab(
    var id: Int? = 0,
    var locationTabName: String? = null,
    var locationTabOrder: Int? = 0,
    var locationModeId: Int? = 0,
    var isActive: Int? = 1
)