package com.smooth.pos.db.dao

import com.smooth.pos.db.respository.CookStatusRepository
import com.smooth.pos.log.KeyLogger.Companion.MESSAGE
import com.smooth.pos.log.KeyLogger.Companion.METHOD
import com.smooth.pos.log.LogSmooth
import com.smooth.pos.model.CookStatus
import com.smooth.pos.model.db.ColumnName
import com.smooth.pos.model.db.sql.CookStatusSQL
import mu.KotlinLogging
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.core.RowMapper
import org.springframework.stereotype.Component
import java.sql.ResultSet

private val logger = KotlinLogging.logger {}

@Component
class CookStatusDAO: CookStatusRepository {
    private var jdbcTemplate: JdbcTemplate? = null

    @Autowired
    private fun init(jdbcTemplate: JdbcTemplate?) {
        this.jdbcTemplate = jdbcTemplate
    }

    override fun add(cookStatus: CookStatus): Boolean {
        var isSuccess: Boolean = false
        try {
            jdbcTemplate?.update(
                CookStatusSQL.SQL_ADD_COOK_STATUS,
                cookStatus.cookStatusName
            )
            logger.info("")
            isSuccess = true
        } catch (ex: Exception) {
            LogSmooth.addKeyValue(METHOD, "add()")
            LogSmooth.addKeyValue(MESSAGE, ex.toString())
            logger.error(LogSmooth.getMessage())
        }
        return isSuccess
    }

    override fun update(cookStatus: CookStatus): Boolean {
        var isSuccess: Boolean = false
        try {
            jdbcTemplate?.update(
                CookStatusSQL.SQL_UPDATE_COOK_STATUS,
                cookStatus.cookStatusName,
                cookStatus.id
            )
            logger.info("")
            isSuccess = true
        } catch (ex: Exception) {
            LogSmooth.addKeyValue(METHOD, "update()")
            LogSmooth.addKeyValue(MESSAGE, ex.toString())
            logger.error(LogSmooth.getMessage())
        }
        return isSuccess
    }

    override fun delete(cookStatusId: Int): Boolean {
        var isSuccess: Boolean = false
        try {
            jdbcTemplate?.update(
                CookStatusSQL.SQL_DELETE_COOK_STATUS,
                cookStatusId
            )
            logger.info("")
            isSuccess = true
        } catch (ex: Exception) {
            LogSmooth.addKeyValue(METHOD, "delete()")
            LogSmooth.addKeyValue("CookStatusId", cookStatusId)
            LogSmooth.addKeyValue(MESSAGE, ex.toString())
            logger.error(LogSmooth.getMessage())
        }
        return isSuccess
    }

    override fun getAll(): List<CookStatus> {
        var cookStatusList: List<CookStatus> = listOf<CookStatus>()
        try {
            cookStatusList = jdbcTemplate?.query(CookStatusSQL.SQL_GET_ALL_COOK_STATUS, cookStatusRowMapper()) as List<CookStatus>
            return cookStatusList
        } catch (ex: Exception) {
            LogSmooth.addKeyValue(METHOD, "getAll()")
            LogSmooth.addKeyValue(MESSAGE, ex.toString())
            logger.error(LogSmooth.getMessage())
        }
        return cookStatusList;
    }

    private fun cookStatusRowMapper(): RowMapper<CookStatus> {
        return RowMapper<CookStatus> { rs: ResultSet, _: Int ->
            CookStatus(
                rs.getInt(ColumnName.COL_COOK_STATUS_ID),
                rs.getString(ColumnName.COL_COOK_STATUS_NAME)
            )
        }
    }
}