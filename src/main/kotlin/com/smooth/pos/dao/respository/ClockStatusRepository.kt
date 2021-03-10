package com.smooth.pos.dao.respository

import com.smooth.pos.model.ClockStatus

interface ClockStatusRepository {
    fun add(clockStatus: ClockStatus): Boolean
    fun update(clockStatus: ClockStatus): Boolean
    fun delete(id: Int): Boolean
    fun getAll(): List<ClockStatus>
}