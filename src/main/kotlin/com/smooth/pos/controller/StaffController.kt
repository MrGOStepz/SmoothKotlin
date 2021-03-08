package com.smooth.pos.controller
import com.smooth.pos.model.Staff
//import com.smooth.pos.model.StaffRequest
import com.smooth.pos.service.StaffService
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.ui.set
import mu.KotlinLogging
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.LogManager.getLogger
import org.apache.logging.log4j.Logger
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

//private val logger = KotlinLogging.logger {}

@RestController
@RequestMapping(path = ["api/v1/staff"])
class StaffController {

    companion object {
        val logger: Logger = LogManager.getLogger()
    }

    var staffService: StaffService? = null

    @Autowired
    fun init(staffService: StaffService) {
        this.staffService = staffService
    }


    @GetMapping
    fun getStaff(): List<Staff>? {
        logger.info("TEST")
        logger.error("Error")
        return staffService?.getAllStaff()
    }


//    @PostMapping
//    fun addNewStaff(@RequestBody staff: StaffRequest?): String? {
////        staffService.addNewStaff(staff)
//        if (staff != null) {
//            return staff.firstName
//        }
//        return "Error"
//    }

}
