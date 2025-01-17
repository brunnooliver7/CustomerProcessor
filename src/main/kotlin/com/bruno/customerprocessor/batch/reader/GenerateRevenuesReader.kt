package com.bruno.customerprocessor.batch.reader

import com.bruno.customerprocessor.batch.utils.FileUtils
import com.bruno.customerprocessor.model.RevenueRead
import org.springframework.batch.item.ItemReader
import org.springframework.batch.item.database.builder.JdbcCursorItemReaderBuilder
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.jdbc.core.RowMapper
import javax.sql.DataSource

@Configuration(value = "GenerateRevenuesReaderConfig")
class GenerateRevenuesReader {

    @Bean
    fun generateRevenuesReader(
        @Qualifier("customersDataSource") dataSource: DataSource
    ): ItemReader<RevenueRead> {
        return JdbcCursorItemReaderBuilder<RevenueRead>()
            .name("Generate Revenues Reader")
            .dataSource(dataSource)
            .sql(FileUtils.readSqlFromFile("sql/read/generate_revenues_reader.sql"))
            .rowMapper(revenueRowMapper())
            .build()
    }

    private fun revenueRowMapper(): RowMapper<RevenueRead> {
        return RowMapper { rs, _ ->
            RevenueRead(
                customerId = rs.getLong("CUSTOMER_ID"),
                bankAccountId = rs.getLong("BANK_ACCOUNT_ID"),
            )
        }
    }

}