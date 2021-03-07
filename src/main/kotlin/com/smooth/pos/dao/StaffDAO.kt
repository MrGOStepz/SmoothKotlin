package com.smooth.pos.dao

import com.smooth.pos.model.ClockStatus
import com.smooth.pos.model.Staff
import com.smooth.pos.model.StaffPosition
import com.smooth.pos.model.db.ColumnName.Companion.COL_CLOCK_STATUS_ID
import com.smooth.pos.model.db.ColumnName.Companion.COL_EMAIL
import com.smooth.pos.model.db.ColumnName.Companion.COL_FIRST_NAME
import com.smooth.pos.model.db.ColumnName.Companion.COL_IS_ACTIVE
import com.smooth.pos.model.db.ColumnName.Companion.COL_LAST_NAME
import com.smooth.pos.model.db.ColumnName.Companion.COL_PASSWORD
import com.smooth.pos.model.db.ColumnName.Companion.COL_PHONE
import com.smooth.pos.model.db.ColumnName.Companion.COL_STAFF_ID
import com.smooth.pos.model.db.ColumnName.Companion.COL_STAFF_POSITION_ID
import com.smooth.pos.model.db.sql.StaffSQL.Companion.SQL_ADD_STAFF
import com.smooth.pos.model.db.sql.StaffSQL.Companion.SQL_DELETE_STAFF
import com.smooth.pos.model.db.sql.StaffSQL.Companion.SQL_GET_ALL_STAFF
import com.smooth.pos.model.db.sql.StaffSQL.Companion.SQL_GET_STAFF_BY_COLUMN
import com.smooth.pos.model.db.sql.StaffSQL.Companion.SQL_GET_STAFF_BY_ID
import com.smooth.pos.model.db.sql.StaffSQL.Companion.SQL_GET_STAFF_BY_PASSWORD
import com.smooth.pos.model.db.sql.StaffSQL.Companion.SQL_UPDATE_STAFF
import mu.KotlinLogging
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.core.RowMapper
import java.sql.ResultSet
import org.springframework.stereotype.Component

private val logger = KotlinLogging.logger {}

@Component
class StaffDAO : PersonRepository {
    private var jdbcTemplate: JdbcTemplate? = null

    @Autowired
    fun init(jdbcTemplate: JdbcTemplate?) {
        this.jdbcTemplate = jdbcTemplate
    }

    override fun add(staff: Staff): Boolean {
        var isSuccess: Boolean = false
        try {
            jdbcTemplate?.update(
                SQL_ADD_STAFF,
                staff.firstName,
                staff.lastName,
                staff.phone,
                staff.email,
                staff.staffPosition,
                staff.clockStatus,
                staff.password,
                staff.isActive
            )
            logger.info("AddStaff")
            isSuccess = true
        } catch (ex: Exception) {
            logger.error(ex.toString())
        }
        return isSuccess
    }

    override fun update(staff: Staff): Boolean {
        var isSuccess: Boolean = false
        try {
            jdbcTemplate?.update(
                SQL_UPDATE_STAFF,
                staff.firstName,
                staff.lastName,
                staff.phone,
                staff.email,
                staff.staffPosition,
                staff.clockStatus,
                staff.password,
                staff.isActive,
                staff.id
            )
            isSuccess = true
        } catch (ex: Exception) {
            logger.error("")
        }
        return isSuccess
    }

    override fun delete(staffId: Int): Boolean {
        var isSuccess: Boolean = false
        try {
            jdbcTemplate?.update(SQL_DELETE_STAFF, staffId)
            isSuccess = true
        } catch (ex: Exception) {
            logger.error("")
        }
        return isSuccess
    }

    override fun getAll(): List<Staff> {
        var staffList: List<Staff> = listOf<Staff>()
        try {
            staffList = jdbcTemplate?.query(SQL_GET_ALL_STAFF, staffRowMapper()) as List<Staff>
            return staffList
        } catch (ex: Exception) {
            logger.error(ex.toString())
        }
        return staffList;
    }

    override fun getById(id: Int): Staff {
        var staff: Staff = Staff()
        try {
            staff = jdbcTemplate?.query(SQL_GET_STAFF_BY_ID, staffRowMapper(), id) as Staff
            return staff
        } catch (ex: Exception) {
            logger.error(ex.toString())
        }
        return staff;
    }

    override fun getByPassword(password: String): Staff {
        var staff: Staff = Staff()
        try {
            staff = jdbcTemplate?.query(SQL_GET_STAFF_BY_PASSWORD, staffRowMapper(), password) as Staff
            return staff
        } catch (ex: Exception) {
            logger.error(ex.toString())
        }
        return staff;
    }

    override fun getByColumn(columnName: String, valueName: String): List<Staff> {
        var staffList: List<Staff> = listOf<Staff>()
        try {
            staffList = jdbcTemplate?.query(SQL_GET_STAFF_BY_COLUMN, staffRowMapper(), columnName, columnName, valueName) as List<Staff>
            return staffList
        } catch (ex: Exception) {
            logger.error(ex.toString())
        }
        return staffList;
    }


    private fun staffRowMapper(): RowMapper<Staff> {
        return RowMapper<Staff> { rs: ResultSet, _: Int ->
            Staff(
                rs.getInt(COL_STAFF_ID),
                rs.getString(COL_FIRST_NAME),
                rs.getString(COL_LAST_NAME),
                rs.getString(COL_PHONE),
                rs.getString(COL_EMAIL),
                StaffPosition(rs.getInt(COL_STAFF_POSITION_ID)),
                ClockStatus(rs.getInt(COL_CLOCK_STATUS_ID)),
                rs.getString(COL_PASSWORD),
                rs.getInt(COL_IS_ACTIVE)
            )
        }
    }

}

