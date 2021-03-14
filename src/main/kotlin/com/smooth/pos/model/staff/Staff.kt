package com.smooth.pos.model

import com.smooth.pos.model.staff.StaffPosition
import com.smooth.pos.model.status.ClockStatus

data class Staff(
    var id: Int? = 0,
    var firstName: String? = null,
    var lastName : String? = null,
    var phone: String? = null,
    var email: String? = null,
    var staffPosition: StaffPosition? = StaffPosition(0,null),
    var clockStatus: ClockStatus? = ClockStatus(0,null),
    var password: String? = null,
    var isActive: Int? = 1
)