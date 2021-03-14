package com.smooth.pos.db.respository

import com.smooth.pos.model.type.PaymentType

interface PaymentTypeRepository {
    fun add(paymentType: PaymentType): Boolean
    fun update(paymentType: PaymentType): Boolean
    fun delete(id: Int): Boolean
    fun getAll(): List<PaymentType>
}