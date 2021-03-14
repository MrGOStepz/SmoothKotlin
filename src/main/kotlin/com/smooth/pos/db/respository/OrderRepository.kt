package com.smooth.pos.db.respository

import com.smooth.pos.model.order.Order

interface OrderRepository {
    fun add(order: Order): Boolean
    fun update(order: Order): Boolean
    fun delete(id: Int): Boolean
    fun getAll(): List<Order>
}