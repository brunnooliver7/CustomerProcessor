package com.bruno.customerprocessor.batch.reader

import com.bruno.customerprocessor.model.CustomerRead
import org.springframework.batch.item.file.FlatFileItemReader
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.io.ClassPathResource
import javax.sql.DataSource

@Configuration(value = "GenerateCustomersReaderConfig")
class GenerateCustomersReader {

    @Bean
    fun generateCustomersReader(
        @Qualifier("customersDataSource") dataSource: DataSource
    ): FlatFileItemReader<CustomerRead> {
        return FlatFileItemReaderBuilder<CustomerRead>()
            .name("Generate Customers Reader")
            .resource(ClassPathResource("csv/customer.csv"))
            .linesToSkip(1)
            .delimited()
            .names("firstName", "lastName", "ssn", "age")
            .fieldSetMapper(BeanWrapperFieldSetMapper<CustomerRead>().apply {
                setTargetType(CustomerRead::class.java)
            })
            .build()
    }

}