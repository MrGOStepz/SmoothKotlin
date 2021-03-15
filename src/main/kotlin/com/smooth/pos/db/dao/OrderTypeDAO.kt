package com.smooth.pos.db.dao

import com.smooth.pos.db.respository.OrderTypeRepository
import com.smooth.pos.log.KeyLogger.Companion.MESSAGE
import com.smooth.pos.log.KeyLogger.Companion.METHOD
import com.smooth.pos.log.LogSmooth
import com.smooth.pos.model.db.ColumnName.Companion.COL_ORDER_TYPE_ID
import com.smooth.pos.model.db.ColumnName.Companion.COL_ORDER_TYPE_NAME
import com.smooth.pos.model.db.sql.OrderTypeSQL
import com.smooth.pos.model.order.OrderType
import mu.KotlinLogging
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.core.RowMapper
import org.springframework.stereotype.Component
import java.sql.ResultSet

private val logger = KotlinLogging.logger {}

@Component
class OrderTypeDAO: OrderTypeRepository {
    private var jdbcTemplate: JdbcTemplate? = null

    @Autowired
    private fun init(jdbcTemplate: JdbcTemplate?) {
        this.jdbcTemplate = jdbcTemplate
    }

    override fun add(orderType: OrderType): Boolean {
        var isSuccess: Boolean = false
        try {
            jdbcTemplate?.update(
                OrderTypeSQL.SQL_ADD_ORDER_TYPE,
                orderType.orderTypeName
            )
            isSuccess = true
        } catch (ex: Exception) {
            LogSmooth.addKeyValue(METHOD, "add()")
            LogSmooth.addKeyValue(MESSAGE, ex.toString())
            logger.error(LogSmooth.getMessage())
        }
        return isSuccess
    }

    override fun update(orderType: OrderType): Boolean {
        var isSuccess: Boolean = false
        try {
            jdbcTemplate?.update(
                OrderTypeSQL.SQL_UPDATE_ORDER_TYPE,
                orderType.orderTypeName,
                orderType.id
            )
            isSuccess = true
        } catch (ex: Exception) {
            LogSmooth.addKeyValue(METHOD, "update()")
            LogSmooth.addKeyValue(MESSAGE, ex.toString())
            logger.error(LogSmooth.getMessage())
        }
        return isSuccess
    }

    override fun delete(orderTypeId: Int): Boolean {
        var isSuccess: Boolean = false
        try {
            jdbcTemplate?.update(
                OrderTypeSQL.SQL_DELETE_ORDER_TYPE,
                orderTypeId
            )
            isSuccess = true
        } catch (ex: Exception) {
            LogSmooth.addKeyValue(METHOD, "delete()")
            LogSmooth.addKeyValue("OrderTypeId", orderTypeId)
            LogSmooth.addKeyValue(MESSAGE, ex.toString())
            logger.error(LogSmooth.getMessage())
        }
        return isSuccess
    }

    override fun getAll(): List<OrderType> {
        var orderTypeList: List<OrderType> = listOf<OrderType>()
        try {
            orderTypeList = jdbcTemplate?.query(OrderTypeSQL.SQL_GET_ALL_ORDER_TYPE, orderTypeRowMapper()) as List<OrderType>
            return orderTypeList
        } catch (ex: Exception) {
            LogSmooth.addKeyValue(METHOD, "getAll()")
            LogSmooth.addKeyValue(MESSAGE, ex.toString())
            logger.error(LogSmooth.getMessage())
        }
        return orderTypeList;
    }

    private fun orderTypeRowMapper(): RowMapper<OrderType> {
        return RowMapper<OrderType> { rs: ResultSet, _: Int ->
            OrderType(
                rs.getInt(COL_ORDER_TYPE_ID),
                rs.getString(COL_ORDER_TYPE_NAME)
            )
        }
    }
}