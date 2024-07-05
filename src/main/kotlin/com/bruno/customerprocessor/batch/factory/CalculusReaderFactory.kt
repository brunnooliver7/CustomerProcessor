package com.bruno.customerprocessor.batch.factory

import com.bruno.customerprocessor.batch.utils.FileUtils
import com.bruno.customerprocessor.model.CalculusRead
import org.springframework.batch.item.ItemReader
import org.springframework.batch.item.database.builder.JdbcCursorItemReaderBuilder
import org.springframework.jdbc.core.RowMapper
import javax.sql.DataSource

object CalculusReaderFactory {

    fun createCalculusReader(
        dataSource: DataSource,
        bankId: Int,
        readerName: String
    ): ItemReader<CalculusRead> {
        val sql = FileUtils
            .readSqlFromFile("sql/read/calculus_reader.sql")
            .replace("\${bank_id}", bankId.toString())

        return JdbcCursorItemReaderBuilder<CalculusRead>()
            .name(readerName)
            .dataSource(dataSource)
            .sql(sql)
            .rowMapper(calculusReaderRowMapper())
            .build()
    }

    private fun calculusReaderRowMapper(): RowMapper<CalculusRead> {
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