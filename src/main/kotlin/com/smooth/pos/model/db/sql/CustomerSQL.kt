package com.smooth.pos.model.db.sql

import com.smooth.pos.model.TableName.Companion.TABLE_CUSTOMER
import com.smooth.pos.model.db.ColumnName.Companion.COL_ADDRESS
import com.smooth.pos.model.db.ColumnName.Companion.COL_CARD
import com.smooth.pos.model.db.ColumnName.Companion.COL_CUSTOMER_ID
import com.smooth.pos.model.db.ColumnName.Companion.COL_DATE_OF_BIRTH
import com.smooth.pos.model.db.ColumnName.Companion.COL_EMAIL
import com.smooth.pos.model.db.ColumnName.Companion.COL_FIRST_NAME
import com.smooth.pos.model.db.ColumnName.Companion.COL_IS_ACTIVE
import com.smooth.pos.model.db.ColumnName.Companion.COL_LAST_ACTIVE
import com.smooth.pos.model.db.ColumnName.Companion.COL_LAST_NAME
import com.smooth.pos.model.db.ColumnName.Companion.COL_PHONE
import com.smooth.pos.model.db.ColumnName.Companion.COL_TOTAL_ORDER

class CustomerSQL {
    companion object {
        const val SQL_GET_ALL_CUSTOMER = "SELECT * FROM $TABLE_CUSTOMER;"
        const val SQL_GET_CUSTOMER_BY_ID = "SELECT * FROM $TABLE_CUSTOMER WHERE $COL_CUSTOMER_ID = ?;"
        const val SQL_GET_CUSTOMER_BY_COLUMN = "SELECT ? FROM $TABLE_CUSTOMER WHERE ? = ?"
        const val SQL_ADD_CUSTOMER = "INSERT INTO $TABLE_CUSTOMER ($COL_FIRST_NAME, $COL_LAST_NAME, $COL_PHONE, $COL_EMAIL, $COL_ADDRESS, $COL_DATE_OF_BIRTH, $COL_LAST_ACTIVE, $COL_CARD, $COL_TOTAL_ORDER, $COL_IS_ACTIVE) VALUE(?,?,?,?,?,?,?,?,?,?);"
        const val SQL_UPDATE_CUSTOMER = "UPDATE $TABLE_CUSTOMER SET($COL_FIRST_NAME = ?, $COL_LAST_NAME = ?, $COL_PHONE = ?, $COL_EMAIL = ?, $COL_ADDRESS = ?,$COL_DATE_OF_BIRTH = ?, $COL_LAST_ACTIVE = ?, $COL_CARD = ?, $COL_TOTAL_ORDER = ?, $COL_IS_ACTIVE = ?) WHERE $COL_CUSTOMER_ID} = ?;"
        const val SQL_DELETE_CUSTOMER = "DELETE FROM $TABLE_CUSTOMER WHERE $COL_CUSTOMER_ID} = ?;"
    }
}