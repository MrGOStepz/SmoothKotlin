package com.smooth.pos.service

import com.smooth.pos.dao.ClockStatusDAO
import com.smooth.pos.dao.respository.ClockStatusRepository
import com.smooth.pos.model.ClockStatus
import org.springframework.beans.factory.annotation.Autowired

class ClockStatusService {
    private var clockStatusRepository: ClockStatusRepository? = null
    private var clockStatus: List<ClockStatus>? = null
    private var mapClockStatus = hashMapOf<Int?,String?>()

    @Autowired
    private fun init(clockStatusDAO: ClockStatusDAO) {
        this.clockStatusRepository = clockStatusDAO
    }

    fun reloadClockStatus() {
        var clockStatus: List<ClockStatus>? = clockStatusRepository?.getAll()
        if(clockStatus != null) {
            this.clockStatus = clockStatus
        }
        mapClockStatus()
    }

    fun getClockStatusList(): List<ClockStatus>? {
        return clockStatus
    }

    fun getMapClockStatus(): HashMap<Int?, String?> {
        return mapClockStatus
    }

    fun getClockNameById(id: Int): String? {
        return try {
            mapClockStatus[id]
        }catch (ex: Exception) {
            null
        }
    }

    private fun mapClockStatus() {
        clockStatus?.forEach {
            mapClockStatus[it.id] = it.clockStatusName
        }
    }
}