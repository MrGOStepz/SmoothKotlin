package com.smooth.pos.db.respository

import com.smooth.pos.model.product.Popup

interface PopupRepository {
    fun add(popup: Popup): Boolean
    fun update(popup: Popup): Boolean
    fun delete(id: Int): Boolean
    fun getAll(): List<Popup>
}