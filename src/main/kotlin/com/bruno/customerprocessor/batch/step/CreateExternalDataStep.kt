package com.bruno.customerprocessor.batch.step

import com.bruno.customerprocessor.batch.tasklet.CreateExternalDataTasklet
import org.springframework.batch.core.Step
import org.springframework.batch.core.repository.JobRepository
import org.springframework.batch.core.step.builder.StepBuilder
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.orm.jpa.JpaTransactionManager

@Configuration
class CreateExternalDataStep {

    @Bean
    fun generateExternalDataStep(
        jobRepository: JobRepository,
        createExternalDataTasklet: CreateExternalDataTasklet,
        @Qualifier("customersJpaTransactionManager") customersJpaTransactionManager: JpaTransactionManager
    ): Step {
        return StepBuilder("Create External Data Step", jobRepository)
            .tasklet(createExternalDataTasklet, customersJpaTransactionManager)
            .transactionManager(customersJpaTransactionManager)
            .build()
    }

}