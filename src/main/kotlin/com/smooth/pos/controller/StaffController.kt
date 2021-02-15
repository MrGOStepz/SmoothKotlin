package com.smooth.pos.controller
import com.smooth.pos.model.StaffRequest
import com.smooth.pos.service.StaffService
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.ui.set
import mu.KotlinLogging
import org.springframework.web.bind.annotation.*

private val logger = KotlinLogging.logger {}

@RestController
@RequestMapping(path = ["api/v1/staff"])
class StaffController {

    val staffService = StaffService()

    @GetMapping
    fun getStaff(): List<String> {
        logger.info("TEST")
        return staffService.getAllStaff()
    }

    @PostMapping
    fun addNewStaff(@RequestBody staff: StaffRequest?): String? {
//        staffService.addNewStaff(staff)
        if (staff != null) {
            return staff.firstName
        }
        return "Error"
    }

}
