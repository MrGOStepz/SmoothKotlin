package com.smooth.pos.db.respository

import com.smooth.pos.model.staff.StaffTimeSheet

interface StaffTimeSheetRepository {
    fun add(staffTimeSheet: StaffTimeSheet): Boolean
    fun update(staffTimeSheet: StaffTimeSheet): Boolean
    fun delete(id: Int): Boolean
    fun getAll(): List<StaffTimeSheet>
}