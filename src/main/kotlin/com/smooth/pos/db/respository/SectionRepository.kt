package com.smooth.pos.db.respository

import com.smooth.pos.model.location.Section

interface SectionRepository {
    fun add(section: Section): Boolean
    fun update(section: Section): Boolean
    fun delete(id: Int): Boolean
    fun getAll(): List<Section>
}