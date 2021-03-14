package com.smooth.pos.db.respository

import com.smooth.pos.model.location.TableSection

interface TableSectionRepository {
    fun add(tableSection: TableSection): Boolean
    fun update(tableSection: TableSection): Boolean
    fun delete(id: Int): Boolean
    fun getAll(): List<TableSection>
}