package com.smooth.pos.db.respository

import com.smooth.pos.model.location.LocationMenu

interface LocationMenuRepository {
    fun add(locationMenu: LocationMenu): Boolean
    fun update(locationMenu: LocationMenu): Boolean
    fun delete(id: Int): Boolean
    fun getAll(): List<LocationMenu>
}