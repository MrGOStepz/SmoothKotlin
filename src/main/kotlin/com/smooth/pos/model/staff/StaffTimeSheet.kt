package com.smooth.pos.model.staff

import com.smooth.pos.model.Staff
import java.util.*

data class StaffTimeSheet(
    var id: Int? = 0,
    var staff: Staff? = Staff(),
    var clockIn: Date? = null,
    var clockOut: Date? = null
)
