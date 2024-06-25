package com.bruno.customerprocessor.batch.step

import com.bruno.customerprocessor.batch.tasklet.CreateExternalDataTasklet
import org.springframework.batch.core.Step
import org.springframework.batch.core.repository.JobRepository
import org.springframework.batch.core.step.builder.StepBuilder
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.jdbc.datasource.DataSourceTransactionManager
import org.springframework.orm.jpa.JpaTransactionManager

@Configuration
class CreateExternalDataStep {

    @Bean
    fun generateExternalDataStep(
        jobRepository: JobRepository,
        createExternalDataTasklet: CreateExternalDataTasklet,
        @Qualifier("customersDataSourceTransactionManager") customersDataSourceTransactionManager: DataSourceTransactionManager,
        @Qualifier("customersJpaTransactionManager") customersJpaTransactionManager: JpaTransactionManager
    ): Step {
        return StepBuilder("createExternalDataStep", jobRepository)
            .tasklet(createExternalDataTasklet, customersDataSourceTransactionManager)
            .transactionManager(customersJpaTransactionManager)
            .build()
    }

}