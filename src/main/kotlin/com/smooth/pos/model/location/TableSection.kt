package com.smooth.pos.model.location

data class TableSection(
    var id: Int? = 0,
    var tableSectionName: String? = null,
    var uniqueName: String? = null,
    var sectionId: Int? = 0,
    var marginTop: Float? = 50.00f,
    var marginBottom: Float? = 50.00f,
    var marginRight: Float? = 50.00f,
    var marginLeft: Float? = 50.00f,
    var height: Float? = 50.00f,
    var left: Float? = 50.00f,
    var isActive: Int? = 1
)
