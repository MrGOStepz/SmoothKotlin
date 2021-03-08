package com.smooth.pos.model.db.sql

import com.smooth.pos.model.TableName
import com.smooth.pos.model.TableName.Companion.TABLE_STAFF
import com.smooth.pos.model.db.ColumnName.Companion.COL_CLOCK_STATUS_ID
import com.smooth.pos.model.db.ColumnName.Companion.COL_EMAIL
import com.smooth.pos.model.db.ColumnName.Companion.COL_FIRST_NAME
import com.smooth.pos.model.db.ColumnName.Companion.COL_IS_ACTIVE
import com.smooth.pos.model.db.ColumnName.Companion.COL_LAST_NAME
import com.smooth.pos.model.db.ColumnName.Companion.COL_PASSWORD
import com.smooth.pos.model.db.ColumnName.Companion.COL_PHONE
import com.smooth.pos.model.db.ColumnName.Companion.COL_STAFF_ID
import com.smooth.pos.model.db.ColumnName.Companion.COL_STAFF_POSITION_ID

class StaffSQL {
    companion object {
        const val SQL_GET_ALL_STAFF = "SELECT * FROM $TABLE_STAFF;"
        const val SQL_GET_STAFF_BY_ID = "SELECT * FROM $TABLE_STAFF WHERE $COL_STAFF_ID = ?;"
        const val SQL_GET_STAFF_BY_PASSWORD = "SELECT * FROM $TABLE_STAFF WHERE $COL_PASSWORD = ?;"
        const val SQL_GET_STAFF_BY_COLUMN = "SELECT ? FROM $TABLE_STAFF WHERE ? = ?"
        const val SQL_ADD_STAFF = "INSERT INTO $TABLE_STAFF ($COL_FIRST_NAME, $COL_LAST_NAME, $COL_PHONE, $COL_EMAIL, $COL_STAFF_POSITION_ID, $COL_CLOCK_STATUS_ID, $COL_PASSWORD, $COL_IS_ACTIVE) VALUE(?,?,?,?,?,?,?,?);"
        const val SQL_UPDATE_STAFF = "UPDATE $TABLE_STAFF SET($COL_FIRST_NAME = ?, $COL_LAST_NAME = ?, $COL_PHONE = ?, $COL_EMAIL = ?, $COL_STAFF_POSITION_ID = ?, $COL_CLOCK_STATUS_ID = ?, $COL_PASSWORD = ?, $COL_IS_ACTIVE = ?) WHERE $COL_STAFF_ID = ?;"
        const val SQL_DELETE_STAFF = "DELETE FROM $TABLE_STAFF WHERE $COL_STAFF_ID = ?;"
    }
}