package com.bruno.customerprocessor.batch.writer

import com.bruno.customerprocessor.batch.utils.FileUtils
import com.bruno.customerprocessor.entity.external.Revenue
import org.springframework.batch.item.database.JdbcBatchItemWriter
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import javax.sql.DataSource

@Configuration(value = "GenerateRevenuesWriterConfig")
class GenerateRevenuesWriter {

    @Bean
    fun generateRevenuesWriter(
        @Qualifier("customersDataSource") dataSource: DataSource
    ): JdbcBatchItemWriter<Revenue> {
        return JdbcBatchItemWriterBuilder<Revenue>()
            .dataSource(dataSource)
            .sql(FileUtils.readSqlFromFile("sql/write/generate_revenues_writer.sql"))
            .beanMapped()
            .build()
    }

}