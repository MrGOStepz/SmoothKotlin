package com.smooth.pos.db.respository

import com.smooth.pos.model.product.ProductIngredient

interface ProductIngredientRepository {
    fun add(productIngredient: ProductIngredient): Boolean
    fun update(productIngredient: ProductIngredient): Boolean
    fun delete(id: Int): Boolean
    fun getAll(): List<ProductIngredient>
}