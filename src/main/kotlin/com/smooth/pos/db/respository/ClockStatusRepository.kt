package com.smooth.pos.db.respository

import com.smooth.pos.model.status.ClockStatus

interface ClockStatusRepository {
    fun add(clockStatus: ClockStatus): Boolean
    fun update(clockStatus: ClockStatus): Boolean
    fun delete(id: Int): Boolean
    fun getAll(): List<ClockStatus>
}