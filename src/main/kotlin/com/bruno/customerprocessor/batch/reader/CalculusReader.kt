package com.bruno.customerprocessor.batch.reader

import com.bruno.customerprocessor.batch.utils.FileUtils
import com.bruno.customerprocessor.model.CalculusRead
import org.springframework.batch.item.ItemReader
import org.springframework.batch.item.database.builder.JdbcCursorItemReaderBuilder
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.jdbc.core.RowMapper
import javax.sql.DataSource

@Configuration(value = "CalculusReaderConfig")
class CalculusReader {

    @Bean
    fun calculusReader(
        @Qualifier("customersDataSource") dataSource: DataSource
    ): ItemReader<CalculusRead> {
        return JdbcCursorItemReaderBuilder<CalculusRead>()
            .name("Calculus Reader")
            .dataSource(dataSource)
            .sql(FileUtils.readSqlFromFile("sql/read/calculus_reader.sql"))
            .rowMapper(calculusResultRowMapper())
            .build()
    }

    private fun calculusResultRowMapper(): RowMapper<CalculusRead> {
        return RowMapper { rs, _ ->
            CalculusRead(
                calculusResultId = rs.getLong("CALCULUS_ID"),
                loanId = rs.getLong("LOAN_ID"),
                debt = rs.getBigDecimal("DEBT"),
                feePercentage = rs.getInt("PERCENTAGE"),
            )
        }
    }

}