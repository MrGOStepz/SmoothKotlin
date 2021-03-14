package com.smooth.pos.db.respository

import com.smooth.pos.model.Setting

interface SettingRepository {
    fun add(setting: Setting): Boolean
    fun update(setting: Setting): Boolean
    fun delete(id: Int): Boolean
    fun getAll(): List<Setting>
}