package com.smooth.pos.db.dao

import com.smooth.pos.db.respository.IngredientRepository
import com.smooth.pos.log.KeyLogger.Companion.MESSAGE
import com.smooth.pos.log.KeyLogger.Companion.METHOD
import com.smooth.pos.log.LogSmooth
import com.smooth.pos.model.db.ColumnName.Companion.COL_INGREDIENT_ID
import com.smooth.pos.model.db.ColumnName.Companion.COL_INGREDIENT_IMAGE_PATH
import com.smooth.pos.model.db.ColumnName.Companion.COL_INGREDIENT_NAME
import com.smooth.pos.model.db.sql.IngredientSQL
import com.smooth.pos.model.product.Ingredient
import mu.KotlinLogging
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.core.RowMapper
import org.springframework.stereotype.Component
import java.sql.ResultSet

private val logger = KotlinLogging.logger {}

@Component
class IngredientDAO: IngredientRepository {
    private var jdbcTemplate: JdbcTemplate? = null

    @Autowired
    private fun init(jdbcTemplate: JdbcTemplate?) {
        this.jdbcTemplate = jdbcTemplate
    }

    override fun add(ingredient: Ingredient): Boolean {
        var isSuccess: Boolean = false
        try {
            jdbcTemplate?.update(
                IngredientSQL.SQL_ADD_INGREDIENT,
                ingredient.ingredientName,
                ingredient.ingredientImagePath
            )
            isSuccess = true
        } catch (ex: Exception) {
            LogSmooth.addKeyValue(METHOD, "add()")
            LogSmooth.addKeyValue(MESSAGE, ex.toString())
            logger.error(LogSmooth.getMessage())
        }
        return isSuccess
    }

    override fun update(ingredient: Ingredient): Boolean {
        var isSuccess: Boolean = false
        try {
            jdbcTemplate?.update(
                IngredientSQL.SQL_UPDATE_INGREDIENT,
                ingredient.ingredientName,
                ingredient.ingredientImagePath,
                ingredient.id
            )
            isSuccess = true
        } catch (ex: Exception) {
            LogSmooth.addKeyValue(METHOD, "update()")
            LogSmooth.addKeyValue(MESSAGE, ex.toString())
            logger.error(LogSmooth.getMessage())
        }
        return isSuccess
    }

    override fun delete(ingredientId: Int): Boolean {
        var isSuccess: Boolean = false
        try {
            jdbcTemplate?.update(
                IngredientSQL.SQL_DELETE_INGREDIENT,
                ingredientId
            )
            isSuccess = true
        } catch (ex: Exception) {
            LogSmooth.addKeyValue(METHOD, "delete()")
            LogSmooth.addKeyValue("IngredientId", ingredientId)
            LogSmooth.addKeyValue(MESSAGE, ex.toString())
            logger.error(LogSmooth.getMessage())
        }
        return isSuccess
    }

    override fun getAll(): List<Ingredient> {
        var ingredientList: List<Ingredient> = listOf<Ingredient>()
        try {
            ingredientList = jdbcTemplate?.query(IngredientSQL.SQL_GET_ALL_INGREDIENT, ingredientRowMapper()) as List<Ingredient>
            return ingredientList
        } catch (ex: Exception) {
            LogSmooth.addKeyValue(METHOD, "getAll()")
            LogSmooth.addKeyValue(MESSAGE, ex.toString())
            logger.error(LogSmooth.getMessage())
        }
        return ingredientList;
    }

    private fun ingredientRowMapper(): RowMapper<Ingredient> {
        return RowMapper<Ingredient> { rs: ResultSet, _: Int ->
            Ingredient(
                rs.getInt(COL_INGREDIENT_ID),
                rs.getString(COL_INGREDIENT_NAME),
                rs.getString(COL_INGREDIENT_IMAGE_PATH)
            )
        }
    }
}