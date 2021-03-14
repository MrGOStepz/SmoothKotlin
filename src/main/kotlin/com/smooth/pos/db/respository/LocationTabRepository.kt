package com.smooth.pos.db.respository

import com.smooth.pos.model.location.LocationTab

interface LocationTabRepository {
    fun add(locationTab: LocationTab): Boolean
    fun update(locationTab: LocationTab): Boolean
    fun delete(id: Int): Boolean
    fun getAll(): List<LocationTab>
}