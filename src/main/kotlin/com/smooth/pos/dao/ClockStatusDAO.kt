package com.smooth.pos.dao

import com.smooth.pos.model.ClockStatus
import com.smooth.pos.model.Staff
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.stereotype.Component

@Component
class ClockStatusDAO {
    private var jdbcTemplate: JdbcTemplate? = null

    @Autowired
    fun init(jdbcTemplate: JdbcTemplate?) {
        this.jdbcTemplate = jdbcTemplate
    }

    fun add(clockStatus: ClockStatus): Int {
        TODO("Not yet implemented")
    }

    fun update(clockStatus: ClockStatus): Int {
        TODO("Not yet implemented")
    }

    fun delete(clockStatusId: Int): Int {
        TODO("Not yet implemented")
    }

    fun getAll(clockStatus: ClockStatus): ClockStatus {
        TODO("Not yet implemented")
    }

    fun getById(clockStatusId : Int) : ClockStatus {
        TODO("Not yet implemented")
    }


}