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

@Configuration
class CalculusResultReader {

    @Bean
    fun calculusResultsReader(@Qualifier("customersDataSource") dataSource: DataSource): ItemReader<CalculusRead> {
        return JdbcCursorItemReaderBuilder<CalculusRead>()
            .name("Calculus Result Reader")
            .dataSource(dataSource)
            .sql(FileUtils.readSqlFromFile("sql/read/calculus_result_reader.sql"))
            .rowMapper(calculusResultRowMapper())
            .build()
    }

    private fun calculusResultRowMapper(): RowMapper<CalculusRead> {
        return RowMapper { rs, _ ->
            CalculusRead(
                calculusResultId = rs.getLong("ID_CALCULUS_RESULT"),
                loanId = rs.getLong("ID_LOAN"),
                debt = rs.getBigDecimal("DEBT"),
                feePercentage = rs.getInt("PERCENTAGE"),
            )
        }
    }

}