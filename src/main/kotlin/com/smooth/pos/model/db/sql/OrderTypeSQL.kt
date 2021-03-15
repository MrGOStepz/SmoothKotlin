package com.smooth.pos.model.db.sql

import com.smooth.pos.model.TableName.Companion.TABLE_ORDER_TYPE
import com.smooth.pos.model.db.ColumnName.Companion.COL_ORDER_TYPE_ID
import com.smooth.pos.model.db.ColumnName.Companion.COL_ORDER_TYPE_NAME

class OrderTypeSQL {
    companion object {
        const val SQL_GET_ALL_ORDER_TYPE = "SELECT * FROM $TABLE_ORDER_TYPE;"
        const val SQL_ADD_ORDER_TYPE = "INSERT INTO $TABLE_ORDER_TYPE ($COL_ORDER_TYPE_NAME) VALUE(?);"
        const val SQL_UPDATE_ORDER_TYPE = "UPDATE $TABLE_ORDER_TYPE SET($COL_ORDER_TYPE_NAME = ?) WHERE $COL_ORDER_TYPE_ID = ?;"
        const val SQL_DELETE_ORDER_TYPE = "DELETE FROM $TABLE_ORDER_TYPE WHERE $COL_ORDER_TYPE_ID} = ?;"
    }
}