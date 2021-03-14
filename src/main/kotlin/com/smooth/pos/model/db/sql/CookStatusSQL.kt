package com.smooth.pos.model.db.sql

import com.smooth.pos.model.TableName.Companion.TABLE_COOK_STATUS
import com.smooth.pos.model.db.ColumnName.Companion.COL_COOK_STATUS_ID
import com.smooth.pos.model.db.ColumnName.Companion.COL_COOK_STATUS_NAME


class CookStatusSQL {
    companion object {
        const val SQL_GET_ALL_COOK_STATUS = "SELECT * FROM $TABLE_COOK_STATUS;"
        const val SQL_ADD_COOK_STATUS = "INSERT INTO $TABLE_COOK_STATUS ($COL_COOK_STATUS_NAME) VALUE(?);"
        const val SQL_UPDATE_COOK_STATUS = "UPDATE $TABLE_COOK_STATUS SET($COL_COOK_STATUS_NAME = ?) WHERE $COL_COOK_STATUS_ID = ?;"
        const val SQL_DELETE_COOK_STATUS = "DELETE FROM $TABLE_COOK_STATUS WHERE $COL_COOK_STATUS_ID} = ?;"
    }
}