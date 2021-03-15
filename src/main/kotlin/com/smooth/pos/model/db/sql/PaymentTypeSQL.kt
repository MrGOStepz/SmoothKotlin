package com.smooth.pos.model.db.sql

import com.smooth.pos.model.TableName.Companion.TABLE_PAYMENT_TYPE
import com.smooth.pos.model.db.ColumnName.Companion.COL_PAYMENT_TYPE_ID
import com.smooth.pos.model.db.ColumnName.Companion.COL_PAYMENT_TYPE_NAME

class PaymentTypeSQL {
    companion object {
        const val SQL_GET_ALL_PAYMENT_TYPE = "SELECT * FROM $TABLE_PAYMENT_TYPE;"
        const val SQL_ADD_PAYMENT_TYPE = "INSERT INTO $TABLE_PAYMENT_TYPE ($COL_PAYMENT_TYPE_NAME) VALUE(?);"
        const val SQL_UPDATE_PAYMENT_TYPE = "UPDATE $TABLE_PAYMENT_TYPE SET($COL_PAYMENT_TYPE_NAME = ?) WHERE $COL_PAYMENT_TYPE_ID = ?;"
        const val SQL_DELETE_PAYMENT_TYPE = "DELETE FROM $TABLE_PAYMENT_TYPE WHERE $COL_PAYMENT_TYPE_ID} = ?;"
    }
}