package com.smooth.pos.db.respository

import com.smooth.pos.model.order.OrderStatus

interface OrderStatusRepository {
    fun add(orderStatus: OrderStatus): Boolean
    fun update(orderStatus: OrderStatus): Boolean
    fun delete(id: Int): Boolean
    fun getAll(): List<OrderStatus>
}