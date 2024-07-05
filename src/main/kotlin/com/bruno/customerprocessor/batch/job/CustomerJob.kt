package com.bruno.customerprocessor.batch.job

import org.springframework.batch.core.Job
import org.springframework.batch.core.Step
import org.springframework.batch.core.job.builder.JobBuilder
import org.springframework.batch.core.job.flow.Flow
import org.springframework.batch.core.repository.JobRepository
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class CustomerJob {

    @Bean
    fun customerJobBean(
        jobRepository: JobRepository,
        @Qualifier("generateDataFlow") generateDataFlow: Flow,
        @Qualifier("populateReferenceTablesStep") populateReferenceTablesStep: Step,
        @Qualifier("collectDataStep") collectDataStep: Step,
        @Qualifier("calculusRuleStep") calculusRuleStep: Step,
        @Qualifier("calculusStep") calculusStep: Step
    ): Job {
        return JobBuilder("Customer Job", jobRepository)
            .start(generateDataFlow)
            .next(populateReferenceTablesStep)
            .next(collectDataStep)
            .next(calculusRuleStep)
            .next(calculusStep)
            .end()
            .build()
    }

}