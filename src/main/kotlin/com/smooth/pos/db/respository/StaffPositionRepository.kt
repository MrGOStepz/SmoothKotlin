package com.smooth.pos.db.respository

import com.smooth.pos.model.staff.StaffPosition

interface StaffPositionRepository {
    fun add(staffPosition: StaffPosition): Boolean
    fun update(staffPosition: StaffPosition): Boolean
    fun delete(id: Int): Boolean
    fun getAll(): List<StaffPosition>
}