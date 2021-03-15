package com.smooth.pos.db.dao

import com.smooth.pos.db.respository.PaymentTypeRepository
import com.smooth.pos.log.KeyLogger.Companion.MESSAGE
import com.smooth.pos.log.KeyLogger.Companion.METHOD
import com.smooth.pos.log.LogSmooth
import com.smooth.pos.model.db.ColumnName.Companion.COL_PAYMENT_TYPE_ID
import com.smooth.pos.model.db.ColumnName.Companion.COL_PAYMENT_TYPE_NAME
import com.smooth.pos.model.db.sql.PaymentTypeSQL
import com.smooth.pos.model.type.PaymentType
import mu.KotlinLogging
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.core.RowMapper
import org.springframework.stereotype.Component
import java.sql.ResultSet

private val logger = KotlinLogging.logger {}

@Component
class PaymentTypeDAO: PaymentTypeRepository {
    private var jdbcTemplate: JdbcTemplate? = null

    @Autowired
    private fun init(jdbcTemplate: JdbcTemplate?) {
        this.jdbcTemplate = jdbcTemplate
    }

    override fun add(paymentType: PaymentType): Boolean {
        var isSuccess: Boolean = false
        try {
            jdbcTemplate?.update(
                PaymentTypeSQL.SQL_ADD_PAYMENT_TYPE,
                paymentType.paymentTypeName
            )
            isSuccess = true
        } catch (ex: Exception) {
            LogSmooth.addKeyValue(METHOD, "add()")
            LogSmooth.addKeyValue(MESSAGE, ex.toString())
            logger.error(LogSmooth.getMessage())
        }
        return isSuccess
    }

    override fun update(paymentType: PaymentType): Boolean {
        var isSuccess: Boolean = false
        try {
            jdbcTemplate?.update(
                PaymentTypeSQL.SQL_UPDATE_PAYMENT_TYPE,
                paymentType.paymentTypeName,
                paymentType.id
            )
            isSuccess = true
        } catch (ex: Exception) {
            LogSmooth.addKeyValue(METHOD, "update()")
            LogSmooth.addKeyValue(MESSAGE, ex.toString())
            logger.error(LogSmooth.getMessage())
        }
        return isSuccess
    }

    override fun delete(paymentTypeId: Int): Boolean {
        var isSuccess: Boolean = false
        try {
            jdbcTemplate?.update(
                PaymentTypeSQL.SQL_DELETE_PAYMENT_TYPE,
                paymentTypeId
            )
            isSuccess = true
        } catch (ex: Exception) {
            LogSmooth.addKeyValue(METHOD, "delete()")
            LogSmooth.addKeyValue("PaymentTypeId", paymentTypeId)
            LogSmooth.addKeyValue(MESSAGE, ex.toString())
            logger.error(LogSmooth.getMessage())
        }
        return isSuccess
    }

    override fun getAll(): List<PaymentType> {
        var paymentTypeList: List<PaymentType> = listOf<PaymentType>()
        try {
            paymentTypeList = jdbcTemplate?.query(PaymentTypeSQL.SQL_GET_ALL_PAYMENT_TYPE, paymentTypeRowMapper()) as List<PaymentType>
            return paymentTypeList
        } catch (ex: Exception) {
            LogSmooth.addKeyValue(METHOD, "getAll()")
            LogSmooth.addKeyValue(MESSAGE, ex.toString())
            logger.error(LogSmooth.getMessage())
        }
        return paymentTypeList;
    }

    private fun paymentTypeRowMapper(): RowMapper<PaymentType> {
        return RowMapper<PaymentType> { rs: ResultSet, _: Int ->
            PaymentType(
                rs.getInt(COL_PAYMENT_TYPE_ID),
                rs.getString(COL_PAYMENT_TYPE_NAME)
            )
        }
    }
}