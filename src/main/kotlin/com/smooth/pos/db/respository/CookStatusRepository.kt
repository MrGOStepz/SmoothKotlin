package com.smooth.pos.db.respository

import com.smooth.pos.model.status.CookStatus

interface CookStatusRepository {
    fun add(cookStatus: CookStatus): Boolean
    fun update(cookStatus: CookStatus): Boolean
    fun delete(id: Int): Boolean
    fun getAll(): List<CookStatus>
}