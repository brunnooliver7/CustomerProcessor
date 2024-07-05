package com.bruno.customerprocessor.batch.reader

import com.bruno.customerprocessor.batch.utils.FileUtils
import com.bruno.customerprocessor.entity.dinamic.LoanRule
import org.springframework.batch.item.ItemReader
import org.springframework.batch.item.database.builder.JdbcCursorItemReaderBuilder
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.jdbc.core.RowMapper
import javax.sql.DataSource

@Configuration
class LoanRuleReader {

    @Bean
    fun loanRulesReader(@Qualifier("customersDataSource") dataSource: DataSource): ItemReader<LoanRule> {
        return JdbcCursorItemReaderBuilder<LoanRule>()
            .name("External Data Reader")
            .dataSource(dataSource)
            .sql(FileUtils.readSqlFromFile("sql/read/loan_rule_reader.sql"))
            .rowMapper(loadRuleRowMapper())
            .build()
    }

    private fun loadRuleRowMapper(): RowMapper<LoanRule> {
        return RowMapper { rs, _ ->
            LoanRule(
                id = rs.getLong("ID_LOAN_RULE"),
                loanId = rs.getLong("ID_LOAN"),
                calculusRuleId = rs.getLong("ID_CALCULUS_RULE")
            )
        }
    }

}