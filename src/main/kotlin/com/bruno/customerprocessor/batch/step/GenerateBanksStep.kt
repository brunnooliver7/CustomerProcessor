package com.bruno.customerprocessor.batch.step

import org.springframework.batch.core.Step
import org.springframework.batch.core.repository.JobRepository
import org.springframework.batch.core.step.builder.StepBuilder
import org.springframework.batch.core.step.tasklet.Tasklet
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.transaction.PlatformTransactionManager

@Configuration(value = "GenerateBanksStepConfig")
class GenerateBanksStep {

    @Bean
    fun generateBanksStep(
        jobRepository: JobRepository,
        transactionManager: PlatformTransactionManager,
        @Qualifier("generateBanksTasklet") generateBanksTasklet: Tasklet
    ): Step {
        return StepBuilder("Generate Banks Step", jobRepository)
            .tasklet(generateBanksTasklet, transactionManager)
            .build()
    }

}