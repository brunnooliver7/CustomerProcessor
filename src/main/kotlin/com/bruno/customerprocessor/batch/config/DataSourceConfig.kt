package com.bruno.customerprocessor.batch.config

import jakarta.persistence.EntityManagerFactory
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary
import org.springframework.jdbc.datasource.DataSourceTransactionManager
import org.springframework.orm.jpa.JpaTransactionManager
import javax.sql.DataSource

@Configuration
class DataSourceConfig {

    @Bean
    @ConfigurationProperties(prefix = "spring.datasource")
    fun customersDataSourceTransactionManager(dataSource: DataSource): DataSourceTransactionManager {
        return DataSourceTransactionManager(dataSource)
    }

    @Bean
    @Primary
    fun customersJpaTransactionManager(
        entityManagerFactory: EntityManagerFactory,
        dataSource: DataSource
    ): JpaTransactionManager {
        val tm = JpaTransactionManager()
        tm.entityManagerFactory = entityManagerFactory
        tm.dataSource = dataSource
        return tm
    }

}