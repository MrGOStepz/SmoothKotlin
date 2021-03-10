package com.smooth.pos.dao

import com.smooth.pos.dao.respository.ClockStatusRepository
import com.smooth.pos.log.KeyLogger.Companion.MESSAGE
import com.smooth.pos.log.KeyLogger.Companion.METHOD
import com.smooth.pos.log.LogSmooth
import com.smooth.pos.model.ClockStatus
import com.smooth.pos.model.db.ColumnName
import com.smooth.pos.model.db.sql.ClockStatusSQL
import mu.KotlinLogging
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.core.RowMapper
import org.springframework.stereotype.Component
import java.sql.ResultSet

private val logger = KotlinLogging.logger {}

@Component
class ClockStatusDAO: ClockStatusRepository {
    private var jdbcTemplate: JdbcTemplate? = null

    @Autowired
    private fun init(jdbcTemplate: JdbcTemplate?) {
        this.jdbcTemplate = jdbcTemplate
    }

    override fun add(clockStatus: ClockStatus): Boolean {
        var isSuccess: Boolean = false
        try {
            jdbcTemplate?.update(
                ClockStatusSQL.SQL_ADD_CLOCK_STATUS,
                clockStatus.clockStatusName
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

    override fun update(clockStatus: ClockStatus): Boolean {
        var isSuccess: Boolean = false
        try {
            jdbcTemplate?.update(
                ClockStatusSQL.SQL_UPDATE_CLOCK_STATUS,
                clockStatus.clockStatusName,
                clockStatus.id
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

    override fun delete(clockStatusId: Int): Boolean {
        var isSuccess: Boolean = false
        try {
            jdbcTemplate?.update(
                ClockStatusSQL.SQL_DELETE_CLOCK_STATUS,
                clockStatusId
            )
            logger.info("")
            isSuccess = true
        } catch (ex: Exception) {
            LogSmooth.addKeyValue(METHOD, "delete()")
            LogSmooth.addKeyValue("ClockStatusId", clockStatusId)
            LogSmooth.addKeyValue(MESSAGE, ex.toString())
            logger.error(LogSmooth.getMessage())
        }
        return isSuccess
    }

    override fun getAll(): List<ClockStatus> {
        var clockStatusList: List<ClockStatus> = listOf<ClockStatus>()
        try {
            clockStatusList = jdbcTemplate?.query(ClockStatusSQL.SQL_GET_ALL_CLOCK_STATUS, clockStatusRowMapper()) as List<ClockStatus>
            return clockStatusList
        } catch (ex: Exception) {
            LogSmooth.addKeyValue(METHOD, "getAll()")
            LogSmooth.addKeyValue(MESSAGE, ex.toString())
            logger.error(LogSmooth.getMessage())
        }
        return clockStatusList;
    }

    private fun clockStatusRowMapper(): RowMapper<ClockStatus> {
        return RowMapper<ClockStatus> { rs: ResultSet, _: Int ->
            ClockStatus(
                rs.getInt(ColumnName.COL_CLOCK_STATUS_ID),
                rs.getString(ColumnName.COL_CLOCK_STATUS_NAME)
            )
        }
    }
}