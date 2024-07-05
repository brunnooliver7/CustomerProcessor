package com.bruno.customerprocessor.batch.reader

import com.bruno.customerprocessor.batch.utils.FileUtils
import com.bruno.customerprocessor.entity.dinamic.CalculusRule
import org.springframework.batch.item.ItemReader
import org.springframework.batch.item.database.builder.JdbcCursorItemReaderBuilder
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.jdbc.core.RowMapper
import javax.sql.DataSource

@Configuration(value = "CalculusRuleReaderConfig")
class CalculusRuleReader {

    @Bean
    fun calculusRuleReader(
        @Qualifier("customersDataSource") dataSource: DataSource
    ): ItemReader<CalculusRule> {
        return JdbcCursorItemReaderBuilder<CalculusRule>()
            .name("Calculus Rule Reader")
            .dataSource(dataSource)
            .sql(FileUtils.readSqlFromFile("sql/read/calculus_rule_reader.sql"))
            .rowMapper(calculusRuleRowMapper())
            .build()
    }

    private fun calculusRuleRowMapper(): RowMapper<CalculusRule> {
        return RowMapper { rs, _ ->
            CalculusRule(
                id = rs.getLong("RULE_ID"),
                loanId = rs.getLong("LOAN_ID"),
                ruleId = rs.getLong("CALCULUS_RULE_ID")
            )
        }
    }

}