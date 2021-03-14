package com.smooth.pos.model.printer

data class Printer(
    var id: Int? =0,
    var printerName: String? = null,
    var printerIp: String? = null,
    var printerPort: String? = null,
    var isActive: Int? = 1
)