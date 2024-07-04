package com.bruno.customerprocessor.batch.reader

import org.springframework.batch.item.ItemReader
import org.springframework.batch.item.database.builder.JdbcCursorItemReaderBuilder
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import javax.sql.DataSource

@Configuration
class GenerateLoansReader {

    @Bean
    fun loanReader(@Qualifier("customersDataSource") dataSource: DataSource): ItemReader<Long> {
        return JdbcCursorItemReaderBuilder<Long>()
            .name("Loan Reader")
            .dataSource(dataSource)
            .sql("SELECT id FROM customer")
            .rowMapper { rs, _ -> rs.getLong("id") }
            .build()
    }

}