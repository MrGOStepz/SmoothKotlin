package com.smooth.pos.db.respository

import com.smooth.pos.model.location.LocationMode

interface LocationModeRepository {
    fun add(locationMode: LocationMode): Boolean
    fun update(locationMode: LocationMode): Boolean
    fun delete(id: Int): Boolean
    fun getAll(): List<LocationMode>
}