package com.smooth.pos.model.order

data class OrderDetail(
    var orderDetailId: Int? = 0,
    var productId: Int? = 0,
    var popupItemId: Int? = 0,
    var orderId: Int? = 0,
    var cookStatusId: Int? = 0,
    var productQuality: Int? = 0,
    var amount: Float? = 0.00f,
    var comment: String? = null,
    var isActive: Int? = 1
)
