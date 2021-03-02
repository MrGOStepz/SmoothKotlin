package com.smooth.pos.dao

import com.smooth.pos.model.ClockStatus
import com.smooth.pos.model.Staff
import com.smooth.pos.model.StaffPosition
import com.smooth.pos.model.TableName.Companion.STAFF_TABLE
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.core.RowMapper
import java.sql.ResultSet
import org.springframework.stereotype.Component

@Component
class StaffDAO : PersonRepository{
    private var jdbcTemplate: JdbcTemplate? = null
    @Autowired
    fun init(jdbcTemplate: JdbcTemplate?) {
        this.jdbcTemplate = jdbcTemplate
    }

    override fun add(staff: Staff): Int {
        TODO("Not yet implemented")
    }

    override fun update(staff: Staff): Int {
        TODO("Not yet implemented")
    }

    override fun delete(staffId: Int): Int {
        TODO("Not yet implemented")
    }

    override fun findAll(): List<Staff> {
        val sql = "SELECT * FROM $STAFF_TABLE"
        var rowMapper : RowMapper<Staff> = RowMapper<Staff> { rs:ResultSet, _: Int ->
            Staff(rs.getInt("id"),
                rs.getString("first_name"),
                rs.getString("last_name"),
                rs.getString("phone"),
                rs.getString("email"),
                StaffPosition(rs.getInt("staff_position_id")),
                ClockStatus(rs.getInt("clock_status_id")),
                "",
                rs.getInt("is_active"))
        }
        return jdbcTemplate?.query(sql,rowMapper) as List<Staff>
    }

    override fun findById(id: Int): Staff {
        TODO("Not yet implemented")
    }

    override fun findByFirstName(firstName: String): Staff {
        TODO("Not yet implemented")
    }

}

