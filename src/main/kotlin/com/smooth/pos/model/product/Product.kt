package com.smooth.pos.model.product

data class Product(
    var id: Int? = 0,
    var productName: String? = null,
    var shortName: String? = null,
    var description: String? = null,
    var isAvailable: Int? = null,
    var productIngredientId: Int? = null,
    var popupId: Int? = null,
    var stock: Int? = null,
    var price: Float? = null,
    var productImagePath: String? = null,
    var foodTypeId: Int? = null,
    var isActive: Int? = null
)




