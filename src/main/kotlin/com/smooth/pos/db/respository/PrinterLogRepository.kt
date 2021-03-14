package com.smooth.pos.db.respository

import com.smooth.pos.model.printer.PrinterLog

interface PrinterLogRepository {
    fun add(printerLog: PrinterLog): Boolean
    fun update(printerLog: PrinterLog): Boolean
    fun delete(id: Int): Boolean
    fun getAll(): List<PrinterLog>
}