package com.smooth.pos.db.respository

import com.smooth.pos.model.product.Product

interface ProductRepository {
    fun add(product: Product): Boolean
    fun update(product: Product): Boolean
    fun delete(id: Int): Boolean
    fun getAll(): List<Product>
}