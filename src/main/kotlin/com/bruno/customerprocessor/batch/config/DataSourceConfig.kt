package com.bruno.customerprocessor.batch.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary
import org.springframework.jdbc.datasource.DataSourceTransactionManager
import org.springframework.orm.jpa.JpaTransactionManager
import javax.sql.DataSource

@Configuration
class DataSourceConfig {

    @Bean
    fun customersDataSourceTransactionManager(dataSource: DataSource): DataSourceTransactionManager {
        return DataSourceTransactionManager(dataSource)
    }

    @Bean
    @Primary
    fun customersJpaTransactionManager(dataSource: DataSource): JpaTransactionManager {
        val tm = JpaTransactionManager()
        tm.dataSource = dataSource
        return tm
    }

}