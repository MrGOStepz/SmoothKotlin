package com.smooth.pos.service

import com.smooth.pos.dao.StaffDAO
import com.smooth.pos.model.Staff
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class StaffService {

    private var staffDAO: StaffDAO? = null

    @Autowired
    fun init(staffDAO: StaffDAO) {
        this.staffDAO = staffDAO
    }

    fun getAllStaff() : List<Staff>? {
        return staffDAO?.getAll();
    }

}