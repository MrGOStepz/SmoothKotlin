package com.smooth.pos.db.respository

import com.smooth.pos.model.product.PopupItem

interface PopupItemRepository{
    fun add(popupItem: PopupItem): Boolean
    fun update(popupItem: PopupItem): Boolean
    fun delete(id: Int): Boolean
    fun getAll(): List<PopupItem>
}