package com.smooth.pos.db.dao

import com.smooth.pos.db.respository.FoodTypeRepository
import com.smooth.pos.log.KeyLogger.Companion.MESSAGE
import com.smooth.pos.log.KeyLogger.Companion.METHOD
import com.smooth.pos.log.LogSmooth
import com.smooth.pos.model.db.ColumnName.Companion.COL_FOOD_TYPE_ID
import com.smooth.pos.model.db.ColumnName.Companion.COL_FOOD_TYPE_NAME
import com.smooth.pos.model.db.sql.FoodTypeSQL
import com.smooth.pos.model.type.FoodType
import mu.KotlinLogging
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.core.RowMapper
import org.springframework.stereotype.Component
import java.sql.ResultSet

private val logger = KotlinLogging.logger {}

@Component
class FoodTypeDAO: FoodTypeRepository {
    private var jdbcTemplate: JdbcTemplate? = null

    @Autowired
    private fun init(jdbcTemplate: JdbcTemplate?) {
        this.jdbcTemplate = jdbcTemplate
    }

    override fun add(foodType: FoodType): Boolean {
        var isSuccess: Boolean = false
        try {
            jdbcTemplate?.update(
                FoodTypeSQL.SQL_ADD_FOOD_TYPE,
                foodType.foodTypeName
            )
            isSuccess = true
        } catch (ex: Exception) {
            LogSmooth.addKeyValue(METHOD, "add()")
            LogSmooth.addKeyValue(MESSAGE, ex.toString())
            logger.error(LogSmooth.getMessage())
        }
        return isSuccess
    }

    override fun update(foodType: FoodType): Boolean {
        var isSuccess: Boolean = false
        try {
            jdbcTemplate?.update(
                FoodTypeSQL.SQL_UPDATE_FOOD_TYPE,
                foodType.foodTypeName,
                foodType.id
            )
            isSuccess = true
        } catch (ex: Exception) {
            LogSmooth.addKeyValue(METHOD, "update()")
            LogSmooth.addKeyValue(MESSAGE, ex.toString())
            logger.error(LogSmooth.getMessage())
        }
        return isSuccess
    }

    override fun delete(foodTypeId: Int): Boolean {
        var isSuccess: Boolean = false
        try {
            jdbcTemplate?.update(
                FoodTypeSQL.SQL_DELETE_FOOD_TYPE,
                foodTypeId
            )
            isSuccess = true
        } catch (ex: Exception) {
            LogSmooth.addKeyValue(METHOD, "delete()")
            LogSmooth.addKeyValue("FoodTypeId", foodTypeId)
            LogSmooth.addKeyValue(MESSAGE, ex.toString())
            logger.error(LogSmooth.getMessage())
        }
        return isSuccess
    }

    override fun getAll(): List<FoodType> {
        var foodTypeList: List<FoodType> = listOf<FoodType>()
        try {
            foodTypeList = jdbcTemplate?.query(FoodTypeSQL.SQL_GET_ALL_FOOD_TYPE, foodTypeRowMapper()) as List<FoodType>
            return foodTypeList
        } catch (ex: Exception) {
            LogSmooth.addKeyValue(METHOD, "getAll()")
            LogSmooth.addKeyValue(MESSAGE, ex.toString())
            logger.error(LogSmooth.getMessage())
        }
        return foodTypeList;
    }

    private fun foodTypeRowMapper(): RowMapper<FoodType> {
        return RowMapper<FoodType> { rs: ResultSet, _: Int ->
            FoodType(
                rs.getInt(COL_FOOD_TYPE_ID),
                rs.getString(COL_FOOD_TYPE_NAME)
            )
        }
    }
}