package com.smooth.pos.model.db.sql

import com.smooth.pos.model.TableName.Companion.TABLE_FOOD_TYPE
import com.smooth.pos.model.db.ColumnName.Companion.COL_FOOD_TYPE_ID
import com.smooth.pos.model.db.ColumnName.Companion.COL_FOOD_TYPE_NAME

class FoodTypeSQL {
    companion object {
        const val SQL_GET_ALL_FOOD_TYPE = "SELECT * FROM $TABLE_FOOD_TYPE;"
        const val SQL_ADD_FOOD_TYPE = "INSERT INTO $TABLE_FOOD_TYPE ($COL_FOOD_TYPE_NAME) VALUE(?);"
        const val SQL_UPDATE_FOOD_TYPE = "UPDATE $TABLE_FOOD_TYPE SET($COL_FOOD_TYPE_NAME = ?) WHERE $COL_FOOD_TYPE_ID = ?;"
        const val SQL_DELETE_FOOD_TYPE = "DELETE FROM $TABLE_FOOD_TYPE WHERE $COL_FOOD_TYPE_ID} = ?;"
    }
}