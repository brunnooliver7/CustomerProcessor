package com.bruno.customerprocessor.batch.reader

import com.bruno.customerprocessor.model.CustomerData
import org.springframework.batch.item.file.FlatFileItemReader
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.io.ClassPathResource
import javax.sql.DataSource

@Configuration
class GenerateCustomersReader {

    @Bean
    fun customersReader(@Qualifier("customersDataSource") dataSource: DataSource): FlatFileItemReader<CustomerData> {
        return FlatFileItemReaderBuilder<CustomerData>()
            .name("generateCustomersReader")
            .resource(ClassPathResource("csv/customer.csv"))
            .linesToSkip(1)
            .delimited()
            .names("firstName", "lastName", "ssn", "age")
            .fieldSetMapper(BeanWrapperFieldSetMapper<CustomerData>().apply {
                setTargetType(CustomerData::class.java)
            })
            .build()
    }

}