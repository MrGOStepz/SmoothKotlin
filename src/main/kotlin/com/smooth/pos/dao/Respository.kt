package com.smooth.pos.dao

import com.smooth.pos.model.Staff

interface PersonRepository {
    fun add(staff: Staff): Int
    fun update(staff: Staff): Int
    fun delete(staffId: Int): Int
    fun findAll(): List<Staff>
    fun findById(id: Int): Staff
    fun findByFirstName(firstName: String): Staff
}