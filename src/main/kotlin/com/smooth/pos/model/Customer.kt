package com.smooth.pos.model

import java.util.*

data class Customer(
    var id: Int? = 0,
    var firstName: String? = null,
    var lastName : String? = null,
    var phone: String? = null,
    var email: String? = null,
    var address: String? = null,
    var totalOrder: Int? = null,
    var lastActive: Date? = null,
    var dateOfBirth: Date? = null,
    var card: String? = null,
    var isActive: Int? = 1
)
