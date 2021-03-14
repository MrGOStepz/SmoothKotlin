package com.smooth.pos.db.respository

import com.smooth.pos.model.Customer

interface CustomerRepository {
    fun add(customer: Customer): Boolean
    fun update(customer: Customer): Boolean
    fun delete(customerId: Int): Boolean
    fun getAll(): List<Customer>
    fun getById(id: Int): Customer
}