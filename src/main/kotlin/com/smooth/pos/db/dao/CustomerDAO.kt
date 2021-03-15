package com.smooth.pos.db.dao

import com.smooth.pos.db.respository.CustomerRepository
import com.smooth.pos.log.KeyLogger
import com.smooth.pos.log.LogSmooth
import com.smooth.pos.model.Customer
import com.smooth.pos.model.db.ColumnName.Companion.COL_ADDRESS
import com.smooth.pos.model.db.ColumnName.Companion.COL_CARD
import com.smooth.pos.model.db.ColumnName.Companion.COL_EMAIL
import com.smooth.pos.model.db.ColumnName.Companion.COL_FIRST_NAME
import com.smooth.pos.model.db.ColumnName.Companion.COL_IS_ACTIVE
import com.smooth.pos.model.db.ColumnName.Companion.COL_LAST_NAME
import com.smooth.pos.model.db.ColumnName.Companion.COL_PHONE
import com.smooth.pos.model.db.ColumnName.Companion.COL_CUSTOMER_ID
import com.smooth.pos.model.db.ColumnName.Companion.COL_DATE_OF_BIRTH
import com.smooth.pos.model.db.ColumnName.Companion.COL_LAST_ACTIVE
import com.smooth.pos.model.db.ColumnName.Companion.COL_TOTAL_ORDER
import com.smooth.pos.model.db.sql.CustomerSQL.Companion.SQL_ADD_CUSTOMER
import com.smooth.pos.model.db.sql.CustomerSQL.Companion.SQL_DELETE_CUSTOMER
import com.smooth.pos.model.db.sql.CustomerSQL.Companion.SQL_GET_ALL_CUSTOMER
import com.smooth.pos.model.db.sql.CustomerSQL.Companion.SQL_GET_CUSTOMER_BY_COLUMN
import com.smooth.pos.model.db.sql.CustomerSQL.Companion.SQL_GET_CUSTOMER_BY_ID
import com.smooth.pos.model.db.sql.CustomerSQL.Companion.SQL_UPDATE_CUSTOMER
import mu.KotlinLogging
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.core.RowMapper
import java.sql.ResultSet
import org.springframework.stereotype.Component

private val logger = KotlinLogging.logger {}

@Component
class CustomerDAO: CustomerRepository {
    private var jdbcTemplate: JdbcTemplate? = null

    @Autowired
    private fun init(jdbcTemplate: JdbcTemplate?) {
        this.jdbcTemplate = jdbcTemplate
    }

    override fun add(customer: Customer): Boolean {
        var isSuccess: Boolean = false
        try {
            jdbcTemplate?.update(
                SQL_ADD_CUSTOMER,
                customer.firstName,
                customer.lastName,
                customer.phone,
                customer.email,
                customer.address,
                customer.dateOfBirth,
                customer.lastActive,
                customer.card,
                customer.totalOrder,
                customer.isActive
            )
            isSuccess = true
        } catch (ex: Exception) {
            LogSmooth.addKeyValue(KeyLogger.METHOD, "add()")
            LogSmooth.addKeyValue(KeyLogger.MESSAGE, ex.toString())
            logger.error(LogSmooth.getMessage())
        }
        return isSuccess
    }

    override fun update(customer: Customer): Boolean {
        var isSuccess: Boolean = false
        try {
            jdbcTemplate?.update(
                SQL_UPDATE_CUSTOMER,
                customer.firstName,
                customer.lastName,
                customer.phone,
                customer.email,
                customer.address,
                customer.dateOfBirth,
                customer.lastActive,
                customer.card,
                customer.totalOrder,
                customer.isActive,
                customer.id
            )
            isSuccess = true
        } catch (ex: Exception) {
            LogSmooth.addKeyValue(KeyLogger.METHOD, "update()")
            LogSmooth.addKeyValue(KeyLogger.MESSAGE, ex.toString())
            logger.error(LogSmooth.getMessage())
        }
        return isSuccess
    }

    override fun delete(customerId: Int): Boolean {
        var isSuccess: Boolean = false
        try {
            jdbcTemplate?.update(SQL_DELETE_CUSTOMER, customerId)
            isSuccess = true
        } catch (ex: Exception) {
            LogSmooth.addKeyValue(KeyLogger.METHOD, "delete()")
            LogSmooth.addKeyValue(KeyLogger.MESSAGE, ex.toString())
            logger.error(LogSmooth.getMessage())
        }
        return isSuccess
    }

    override fun getAll(): List<Customer> {
        var customerList: List<Customer> = listOf<Customer>()
        try {
            customerList = jdbcTemplate?.query(SQL_GET_ALL_CUSTOMER, customerRowMapper()) as List<Customer>
            return customerList
        } catch (ex: Exception) {
            LogSmooth.addKeyValue(KeyLogger.METHOD, "getAll()")
            LogSmooth.addKeyValue(KeyLogger.MESSAGE, ex.toString())
            logger.error(LogSmooth.getMessage())
        }
        return customerList;
    }

    override fun getById(id: Int): Customer {
        var customer: Customer = Customer()
        try {
            customer = jdbcTemplate?.query(SQL_GET_CUSTOMER_BY_ID, customerRowMapper(), id) as Customer
            return customer
        } catch (ex: Exception) {
            LogSmooth.addKeyValue(KeyLogger.METHOD, "getById()")
            LogSmooth.addKeyValue(KeyLogger.MESSAGE, ex.toString())
            logger.error(LogSmooth.getMessage())
        }
        return customer;
    }

    override fun getByColumn(columnName: String, valueName: String): List<Customer> {
        var customerList: List<Customer> = listOf<Customer>()
        try {
            customerList = jdbcTemplate?.query(SQL_GET_CUSTOMER_BY_COLUMN, customerRowMapper(), columnName, columnName, valueName) as List<Customer>
            return customerList
        } catch (ex: Exception) {
            LogSmooth.addKeyValue(KeyLogger.METHOD, "getByColumn()")
            LogSmooth.addKeyValue(KeyLogger.MESSAGE, ex.toString())
            logger.error(LogSmooth.getMessage())
        }
        return customerList;
    }


    private fun customerRowMapper(): RowMapper<Customer> {
        return RowMapper<Customer> { rs: ResultSet, _: Int ->
            Customer(
                rs.getInt(COL_CUSTOMER_ID),
                rs.getString(COL_FIRST_NAME),
                rs.getString(COL_LAST_NAME),
                rs.getString(COL_PHONE),
                rs.getString(COL_EMAIL),
                rs.getString(COL_ADDRESS),
                rs.getDate(COL_DATE_OF_BIRTH),
                rs.getDate(COL_LAST_ACTIVE),
                rs.getString(COL_CARD),
                rs.getInt(COL_TOTAL_ORDER),
                rs.getInt(COL_IS_ACTIVE)
            )
        }
    }

}

