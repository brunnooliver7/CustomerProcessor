package com.bruno.customerprocessor.batch.reader

import com.bruno.customerprocessor.batch.utils.FileUtils
import com.bruno.customerprocessor.entity.dinamic.ExternalData
import org.springframework.batch.item.ItemReader
import org.springframework.batch.item.database.builder.JdbcCursorItemReaderBuilder
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.jdbc.core.RowMapper
import java.time.LocalDate
import javax.sql.DataSource

@Configuration(value = "CollectDataReaderConfig")
class CollectDataReader {

    @Bean
    fun collectDataReader(
        @Qualifier("customersDataSource") dataSource: DataSource
    ): ItemReader<ExternalData> {
        return JdbcCursorItemReaderBuilder<ExternalData>()
            .name("Collect Data Reader")
            .dataSource(dataSource)
            .sql(FileUtils.readSqlFromFile("sql/read/collect_data_reader.sql"))
            .rowMapper(externalDataRowMapper())
            .build()
    }

    private fun externalDataRowMapper(): RowMapper<ExternalData> {
        return RowMapper { rs, _ ->
            ExternalData(
                id = rs.getLong("ID"),
                ssn = rs.getString("SSN"),
                bankId = rs.getLong("BANK_ID"),
                bankName = rs.getString("BANK_NAME"),
                bankAccountNumber = rs.getString("BANK_ACCOUNT_NUMBER"),
                bankAccountBalance = rs.getBigDecimal("BANK_ACCOUNT_BALANCE"),
                revenueAmount = rs.getBigDecimal("REVENUE_AMOUNT"),
                loanId = rs.getLong("LOAN_ID"),
                loan = rs.getBigDecimal("LOAN"),
                payed = rs.getBigDecimal("PAYED"),
                debt = rs.getBigDecimal("DEBT"),
                delay = rs.getInt("DELAY"),
                createdAt = LocalDate.now()
            )
        }
    }

}