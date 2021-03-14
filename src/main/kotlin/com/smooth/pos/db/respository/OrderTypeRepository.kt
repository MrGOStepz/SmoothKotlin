package com.smooth.pos.db.respository

import com.smooth.pos.model.order.OrderType

interface OrderTypeRepository {
    fun add(orderType: OrderType): Boolean
    fun update(orderType: OrderType): Boolean
    fun delete(id: Int): Boolean
    fun getAll(): List<OrderType>
}