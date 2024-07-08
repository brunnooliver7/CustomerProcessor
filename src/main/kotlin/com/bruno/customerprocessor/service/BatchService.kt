package com.bruno.customerprocessor.service

import org.springframework.batch.core.Job
import org.springframework.batch.core.JobParametersBuilder
import org.springframework.batch.core.JobParametersInvalidException
import org.springframework.batch.core.launch.JobLauncher
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException
import org.springframework.batch.core.repository.JobRestartException
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Service

@Service
class BatchService(
    private val jobLauncher: JobLauncher,
    @Qualifier("dataJob") private val dataJob: Job,
    @Qualifier("customerJob") private val customerJob: Job
) {

    fun triggerDataJob() {
        try {
            val params = JobParametersBuilder()
                .addString("JobId", System.currentTimeMillis().toString())
                .toJobParameters()
            jobLauncher.run(dataJob, params)
        } catch (e: Exception) {
            when (e) {
                is JobInstanceAlreadyCompleteException,
                is JobExecutionAlreadyRunningException,
                is JobParametersInvalidException,
                is JobRestartException -> {
                    throw RuntimeException("Error triggering data job")
                }

                else -> throw e
            }
        }
    }

    fun triggerCustomerJob() {
        try {
            val params = JobParametersBuilder()
                .addString("JobId", System.currentTimeMillis().toString())
                .toJobParameters()
            jobLauncher.run(customerJob, params)
        } catch (e: Exception) {
            when (e) {
                is JobInstanceAlreadyCompleteException,
                is JobExecutionAlreadyRunningException,
                is JobParametersInvalidException,
                is JobRestartException -> {
                    throw RuntimeException("Error triggering customer job")
                }

                else -> throw e
            }
        }
    }

}