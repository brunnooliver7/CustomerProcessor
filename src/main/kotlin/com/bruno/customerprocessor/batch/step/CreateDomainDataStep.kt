package com.bruno.customerprocessor.batch.step

import com.bruno.customerprocessor.batch.tasklet.CreateDomainDataTasklet
import org.springframework.batch.core.Step
import org.springframework.batch.core.repository.JobRepository
import org.springframework.batch.core.step.builder.StepBuilder
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.orm.jpa.JpaTransactionManager

@Configuration
class CreateDomainDataStep {

    @Bean
    fun generateDomainDataStep(
        jobRepository: JobRepository,
        createDomainDataTasklet: CreateDomainDataTasklet,
        @Qualifier("customersJpaTransactionManager") customersJpaTransactionManager: JpaTransactionManager
    ): Step {
        return StepBuilder("Create Domain Data Step", jobRepository)
            .tasklet(createDomainDataTasklet, customersJpaTransactionManager)
            .transactionManager(customersJpaTransactionManager)
            .build()
    }

}