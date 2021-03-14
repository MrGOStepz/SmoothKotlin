package com.smooth.pos.controller
import com.smooth.pos.log.LogSmooth
import com.smooth.pos.model.Staff
import com.smooth.pos.service.StaffService
import mu.KotlinLogging
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

private val logger = KotlinLogging.logger {}

@RestController
@RequestMapping(path = ["api/v1/staff"])
class StaffController {
    var staffService: StaffService? = null

    @Autowired
    fun init(staffService: StaffService) {
        this.staffService = staffService
    }


    @GetMapping
    fun getStaff(): List<Staff>? {
        return staffService?.getAllStaff()
    }


    @PostMapping
    fun addNewStaff(@RequestBody staff: StaffRequest?): String? {
        if (staff != null) {
            return staff.firstName
        }
        return "Error"
    }

}
