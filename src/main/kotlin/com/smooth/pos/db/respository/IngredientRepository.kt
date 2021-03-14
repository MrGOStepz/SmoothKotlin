package com.smooth.pos.db.respository

import com.smooth.pos.model.product.Ingredient

interface IngredientRepository {
    fun add(ingredient: Ingredient): Boolean
    fun update(ingredient: Ingredient): Boolean
    fun delete(id: Int): Boolean
    fun getAll(): List<Ingredient>
}