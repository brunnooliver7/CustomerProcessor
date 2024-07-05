package com.bruno.customerprocessor.batch.reader

import org.springframework.batch.item.ItemReader
import org.springframework.batch.item.database.builder.JdbcCursorItemReaderBuilder
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import javax.sql.DataSource

@Configuration(value = "GenerateLoansReaderConfig")
class GenerateLoansReader {

    @Bean
    fun generateLoansReader(
        @Qualifier("customersDataSource") dataSource: DataSource
    ): ItemReader<Long> {
        return JdbcCursorItemReaderBuilder<Long>()
            .name("Generate Loans Reader")
            .dataSource(dataSource)
            .sql("SELECT id FROM customer")
            .rowMapper { rs, _ -> rs.getLong("id") }
            .build()
    }

}