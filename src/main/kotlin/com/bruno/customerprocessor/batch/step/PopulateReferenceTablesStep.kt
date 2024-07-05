package com.bruno.customerprocessor.batch.step

import com.bruno.customerprocessor.batch.tasklet.PopulateReferenceTablesTasklet
import org.springframework.batch.core.Step
import org.springframework.batch.core.repository.JobRepository
import org.springframework.batch.core.step.builder.StepBuilder
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.orm.jpa.JpaTransactionManager

@Configuration(value = "PopulateReferenceTablesStepConfig")
class PopulateReferenceTablesStep {

    @Bean
    fun populateReferenceTablesStep(
        jobRepository: JobRepository,
        @Qualifier("populateReferenceTablesTasklet") populateReferenceTablesTasklet: PopulateReferenceTablesTasklet,
        @Qualifier("customersJpaTransactionManager") customersJpaTransactionManager: JpaTransactionManager
    ): Step {
        return StepBuilder("Populate Reference Tables Step", jobRepository)
            .tasklet(populateReferenceTablesTasklet, customersJpaTransactionManager)
            .transactionManager(customersJpaTransactionManager)
            .build()
    }

}