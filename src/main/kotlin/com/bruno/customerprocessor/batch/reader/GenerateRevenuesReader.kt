package com.bruno.customerprocessor.batch.reader

import com.bruno.customerprocessor.batch.utils.FileUtils
import com.bruno.customerprocessor.model.RevenueData
import org.springframework.batch.item.ItemReader
import org.springframework.batch.item.database.builder.JdbcCursorItemReaderBuilder
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.jdbc.core.RowMapper
import javax.sql.DataSource

@Configuration
class GenerateRevenuesReader {

    @Bean
    fun revenueReader(@Qualifier("customersDataSource") dataSource: DataSource): ItemReader<RevenueData> {
        return JdbcCursorItemReaderBuilder<RevenueData>()
            .name("Revenue Reader")
            .dataSource(dataSource)
            .sql(FileUtils.readSqlFromFile("sql/read/revenue_reader.sql"))
            .rowMapper(revenueRowMapper())
            .build()
    }

    private fun revenueRowMapper(): RowMapper<RevenueData> {
        return RowMapper { rs, _ ->
            RevenueData(
                customerId = rs.getLong("CUSTOMER_ID"),
                bankAccountId = rs.getLong("BANK_ACCOUNT_ID"),
            )
        }
    }

}