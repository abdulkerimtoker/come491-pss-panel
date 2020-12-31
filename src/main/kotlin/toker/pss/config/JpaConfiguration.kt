package toker.pss.config

import org.apache.commons.dbcp2.BasicDataSource
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import javax.sql.DataSource

@Configuration
@EnableJpaRepositories
class JpaConfiguration {

    @Bean
    fun dataSource(): DataSource {
        val dataSource = BasicDataSource()
        dataSource.driverClassName = "org.postgresql.Driver"
        dataSource.url = String.format(
            "jdbc:postgresql://%s:%s/%s",
            "127.0.0.1",
            "5432",
            "pss"
        )
        dataSource.username = "pss"
        dataSource.password = "12345"
        dataSource.initialSize = 4
        return dataSource
    }
}