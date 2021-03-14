package com.smooth.pos.db.respository

import com.smooth.pos.model.printer.PrinterProduct

interface PrinterProductRepository {
    fun add(printerProduct: PrinterProduct): Boolean
    fun update(printerProduct: PrinterProduct): Boolean
    fun delete(id: Int): Boolean
    fun getAll(): List<PrinterProduct>
}