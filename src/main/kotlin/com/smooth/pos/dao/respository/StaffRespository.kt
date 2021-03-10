package com.smooth.pos.dao

import com.smooth.pos.model.Staff

interface StaffRepository {
    fun add(staff: Staff): Boolean
    fun update(staff: Staff): Boolean
    fun delete(staffId: Int): Boolean
    fun getAll(): List<Staff>
    fun getById(id: Int): Staff
    fun getByPassword(password: String): Staff
    fun getByColumn(columnName: String, valueName: String): List<Staff>
}