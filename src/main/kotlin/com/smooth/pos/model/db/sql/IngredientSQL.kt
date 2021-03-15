package com.smooth.pos.model.db.sql

import com.smooth.pos.model.TableName.Companion.TABLE_INGREDIENT
import com.smooth.pos.model.db.ColumnName.Companion.COL_INGREDIENT_ID
import com.smooth.pos.model.db.ColumnName.Companion.COL_INGREDIENT_IMAGE_PATH
import com.smooth.pos.model.db.ColumnName.Companion.COL_INGREDIENT_NAME

class IngredientSQL {
    companion object {
        const val SQL_GET_ALL_INGREDIENT = "SELECT * FROM $TABLE_INGREDIENT;"
        const val SQL_ADD_INGREDIENT = "INSERT INTO $TABLE_INGREDIENT ($COL_INGREDIENT_NAME, $COL_INGREDIENT_IMAGE_PATH) VALUE(?,?);"
        const val SQL_UPDATE_INGREDIENT = "UPDATE $TABLE_INGREDIENT SET($COL_INGREDIENT_NAME = ?, , $COL_INGREDIENT_IMAGE_PATH = ?) WHERE $COL_INGREDIENT_ID = ?;"
        const val SQL_DELETE_INGREDIENT = "DELETE FROM $TABLE_INGREDIENT WHERE $COL_INGREDIENT_ID} = ?;"
    }
}