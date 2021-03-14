package com.smooth.pos.model.product

data class Popup(
    var id: Int? = 0,
    var popupName: String? = null,
    var isActive: Int? = 1
)

data class PopupItem(
    var id: Int? = 0,
    var popupItemName: String? = null,
    var popupId: Int? = 0,
    var price: Float? = 0.00f,
    var popupImagePath: String? = null
)
