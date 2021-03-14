package com.smooth.pos.db.respository

import com.smooth.pos.model.order.OrderDetail

interface OrderDetailRepository {
    fun add(orderDetail: OrderDetail): Boolean
    fun update(orderDetail: OrderDetail): Boolean
    fun delete(id: Int): Boolean
    fun getAll(): List<OrderDetail>
}