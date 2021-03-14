package com.smooth.pos.db.respository

import com.smooth.pos.model.type.FoodType

interface FoodTypeRepository {
    fun add(foodType: FoodType): Boolean
    fun update(foodType: FoodType): Boolean
    fun delete(id: Int): Boolean
    fun getAll(): List<FoodType>
}