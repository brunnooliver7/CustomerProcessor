package com.bruno.customerprocessor.batch.writer

import com.bruno.customerprocessor.batch.utils.FileUtils
import com.bruno.customerprocessor.entity.external.Customer
import org.springframework.batch.item.database.JdbcBatchItemWriter
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import javax.sql.DataSource

@Configuration(value = "GenerateCustomerWriterConfig")
class GenerateCustomerWriter {

    @Bean
    fun generateCustomerWriter(
        @Qualifier("customersDataSource") dataSource: DataSource
    ): JdbcBatchItemWriter<Customer> {
        return JdbcBatchItemWriterBuilder<Customer>()
            .dataSource(dataSource)
            .sql(FileUtils.readSqlFromFile("sql/write/generate_customer_writer.sql"))
            .beanMapped()
            .build()
    }

}