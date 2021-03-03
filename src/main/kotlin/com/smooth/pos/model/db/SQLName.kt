package com.smooth.pos.model.db

import com.smooth.pos.model.TableName

class SQLName {
    companion object {
        const val SQL_GET_ALL_STAFF = "SELECT * FROM ${TableName.STAFF_TABLE}"
        const val COOK_STATUS_TABLE = "cook_status"
    }
}