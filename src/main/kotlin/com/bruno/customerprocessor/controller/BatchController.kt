package com.bruno.customerprocessor.controller

import com.bruno.customerprocessor.service.BatchService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api")
class BatchController(private val batchService: BatchService) {

    @PostMapping("/data-job")
    fun triggerDataJob() {
        batchService.triggerDataJob()
    }

    @PostMapping("/customer-job")
    fun triggerCustomerJob() {
        batchService.triggerCustomerJob()
    }

}