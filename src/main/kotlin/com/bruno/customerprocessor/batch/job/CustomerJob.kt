package com.bruno.customerprocessor.batch.job

import org.springframework.batch.core.BatchStatus
import org.springframework.batch.core.Job
import org.springframework.batch.core.Step
import org.springframework.batch.core.job.builder.JobBuilder
import org.springframework.batch.core.job.flow.Flow
import org.springframework.batch.core.repository.JobRepository
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration(value = "CustomerJobConfig")
class CustomerJob {

    @Bean
    fun customerJob(
        jobRepository: JobRepository,
        @Qualifier("collectDataStep") collectDataStep: Step,
        @Qualifier("calculusRuleStep") calculusRuleStep: Step,
        @Qualifier("calculusFlow") calculusFlow: Flow
    ): Job {
        return JobBuilder("Customer Job", jobRepository)
            .start(collectDataStep)
            .next(calculusRuleStep).on(BatchStatus.COMPLETED.toString())
            .to(calculusFlow)
            .end()
            .build()
    }

}