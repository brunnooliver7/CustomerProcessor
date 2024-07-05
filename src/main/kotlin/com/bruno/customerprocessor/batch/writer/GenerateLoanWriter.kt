package com.bruno.customerprocessor.batch.writer

import com.bruno.customerprocessor.batch.utils.FileUtils
import com.bruno.customerprocessor.entity.external.Loan
import org.springframework.batch.item.ItemWriter
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate
import javax.sql.DataSource

@Configuration(value = "GenerateLoanWriterConfig")
class GenerateLoanWriter {

    @Bean
    fun generateLoansWriter(
        @Qualifier("customersDataSource") dataSource: DataSource
    ): ItemWriter<List<Loan>> {
        return ItemWriter { loans ->
            val jdbcTemplate = NamedParameterJdbcTemplate(dataSource)
            val sql = FileUtils.readSqlFromFile("sql/write/generate_loans_writer.sql")

            loans.flatten().let { loanList ->
                val paramsArray = loanList.map { BeanPropertySqlParameterSource(it) }.toTypedArray()
                jdbcTemplate.batchUpdate(sql, paramsArray)
            }
        }
    }

}