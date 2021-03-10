package com.smooth.pos.model

import java.util.*

data class StaffTimesheet(
    var id: Int? = 0,
    var staff: Staff? = Staff(),
    var clockIn: Date? = null,
    var clockOut: Date? = null
)

data class ClockStatus (
    var id: Int? = 0,
    var clockStatusName: String? = null
)

data class CookStatus (
    var id: Int? = 0,
    var cookStatusName: String? = null
)

