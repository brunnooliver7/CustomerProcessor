package com.bruno.customerprocessor.batch.config

import jakarta.persistence.EntityManagerFactory
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.jdbc.DataSourceBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary
import org.springframework.orm.jpa.JpaTransactionManager
import javax.sql.DataSource

@Configuration
class DataSourceConfig {

    @Bean
    @ConfigurationProperties(prefix = "spring.datasource")
    fun customersDataSource(): DataSource {
        return DataSourceBuilder.create().build()
    }

    @Bean
    @Primary
    fun customersJpaTransactionManager(
        entityManagerFactory: EntityManagerFactory,
        @Qualifier("customersDataSource") dataSource: DataSource
    ): JpaTransactionManager {
        val tm = JpaTransactionManager()
        tm.entityManagerFactory = entityManagerFactory
        tm.dataSource = dataSource
        return tm
    }

}