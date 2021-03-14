package com.smooth.pos.service

import com.smooth.pos.db.dao.StaffDAO
import com.smooth.pos.db.respository.ClockStatusRepository
import com.smooth.pos.db.respository.StaffRepository
import com.smooth.pos.model.Staff
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class StaffService {

    private var staffRepository: StaffRepository? = null

    @Autowired
    fun init(staffDAO: StaffDAO) {
        this.staffRepository = staffDAO
    }

    fun getAllStaff() : List<Staff>? {
        return staffRepository?.getAll();
    }

}