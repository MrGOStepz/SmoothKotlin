package com.smooth.pos.dao

import com.smooth.pos.model.Staff

interface PersonRepository {
    fun add(staff: Staff): Int
    fun update(staff: Staff): Int
    fun delete(staffId: Int): Int
    fun getAll(): List<Staff>
    fun getById(id: Int): Staff
    fun getByFirstName(firstName: String): Staff
}