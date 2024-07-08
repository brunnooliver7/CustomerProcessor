package com.bruno.customerprocessor.batch.job

import org.springframework.batch.core.Job
import org.springframework.batch.core.Step
import org.springframework.batch.core.job.builder.JobBuilder
import org.springframework.batch.core.job.flow.Flow
import org.springframework.batch.core.repository.JobRepository
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration(value = "DataJobConfig")
class DataJob {

    @Bean
    fun dataJob(
        jobRepository: JobRepository,
        @Qualifier("generateDataFlow") generateDataFlow: Flow,
        @Qualifier("populateReferenceTablesStep") populateReferenceTablesStep: Step,
    ): Job {
        return JobBuilder("Data Job", jobRepository)
            .start(generateDataFlow)
            .next(populateReferenceTablesStep)
            .end()
            .build()
    }

}