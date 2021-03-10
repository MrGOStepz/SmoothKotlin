package com.smooth.pos.model.db.sql

import com.smooth.pos.model.TableName.Companion.TABLE_CLOCK_STATUS
import com.smooth.pos.model.db.ColumnName.Companion.COL_CLOCK_STATUS_ID
import com.smooth.pos.model.db.ColumnName.Companion.COL_CLOCK_STATUS_NAME

class ClockStatusSQL {
    companion object {
        const val SQL_GET_ALL_CLOCK_STATUS = "SELECT * FROM $TABLE_CLOCK_STATUS;"
        const val SQL_ADD_CLOCK_STATUS = "INSERT INTO $TABLE_CLOCK_STATUS ($COL_CLOCK_STATUS_NAME) VALUE(?);"
        const val SQL_UPDATE_CLOCK_STATUS = "UPDATE $TABLE_CLOCK_STATUS SET($COL_CLOCK_STATUS_NAME = ?) WHERE $COL_CLOCK_STATUS_ID = ?;"
        const val SQL_DELETE_CLOCK_STATUS = "DELETE FROM $TABLE_CLOCK_STATUS WHERE $COL_CLOCK_STATUS_ID} = ?;"
    }
}