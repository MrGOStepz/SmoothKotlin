package com.smooth.pos.model.order

import java.util.*

data class Order(
    var id: Int? = 0,
    var orderAt: Date? = null,
    var orderTypeId: Int? = 0,
    var staffId: Int? = 0,
    var tableDectionId: Int? = 0,
    var orderStatusId: Int? = 0,
    var paymentId: Int? = 0,
    var customerId: Int? = 0,
    var isActive: Int? = null
)
