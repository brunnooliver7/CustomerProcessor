package com.bruno.customerprocessor.batch.job

import org.springframework.batch.core.Job
import org.springframework.batch.core.Step
import org.springframework.batch.core.job.builder.JobBuilder
import org.springframework.batch.core.repository.JobRepository
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class CustomerJob {

    @Bean
    fun customerJobBean(
        jobRepository: JobRepository,
        @Qualifier("generateExternalDataStep") generateExternalDataStep: Step,
        @Qualifier("generateDomainDataStep") generateDomainDataStep: Step
    ): Job {
        return JobBuilder("Customer Job", jobRepository)
            .start(generateExternalDataStep)
            .next(generateDomainDataStep)
            .build()
    }

}