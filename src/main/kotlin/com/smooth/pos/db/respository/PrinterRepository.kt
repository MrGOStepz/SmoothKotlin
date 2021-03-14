package com.smooth.pos.db.respository

import com.smooth.pos.model.printer.Printer

interface PrinterRepository {
    fun add(printer: Printer): Boolean
    fun update(printer: Printer): Boolean
    fun delete(id: Int): Boolean
    fun getAll(): List<Printer>
}