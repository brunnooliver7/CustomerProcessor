package com.bruno.customerprocessor.batch.step

import com.bruno.customerprocessor.entity.external.Customer
import com.bruno.customerprocessor.model.CustomerData
import org.springframework.batch.core.Step
import org.springframework.batch.core.repository.JobRepository
import org.springframework.batch.core.step.builder.StepBuilder
import org.springframework.batch.item.database.JdbcBatchItemWriter
import org.springframework.batch.item.file.FlatFileItemReader
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.transaction.PlatformTransactionManager

@Configuration
class GenerateCustomerDataStep {

    @Bean
    fun generateCustomerDataStepBean(
        jobRepository: JobRepository,
        transactionManager: PlatformTransactionManager,
        @Qualifier("customersReader") generateCustomersReader: FlatFileItemReader<CustomerData>,
        @Qualifier("customerWriter") generateCustomerWriter: JdbcBatchItemWriter<Customer>
    ): Step {
        return StepBuilder("Generate Customer Data Step", jobRepository)
            .chunk<CustomerData, Customer>(20_000, transactionManager)
            .reader(generateCustomersReader)
            .writer(generateCustomerWriter)
            .build()
    }

}