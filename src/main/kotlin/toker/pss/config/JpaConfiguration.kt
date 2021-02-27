package toker.pss.config

import org.apache.commons.dbcp2.BasicDataSource
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.data.web.config.EnableSpringDataWebSupport
import org.springframework.web.servlet.config.annotation.EnableWebMvc
import toker.pss.repo.BaseRepository
import javax.sql.DataSource

@Configuration
@EnableJpaRepositories(basePackageClasses = [BaseRepository::class])
class JpaConfiguration {

    @Value("\${db-host}")
    lateinit var host: String

    @Value("\${db-port}")
    lateinit var port: String

    @Value("\${db-name}")
    lateinit var name: String

    @Value("\${db-user}")
    lateinit var user: String

    @Value("\${db-pass}")
    lateinit var pass: String

    @Bean
    fun dataSource(): DataSource {
        val dataSource = BasicDataSource()
        dataSource.driverClassName = "org.postgresql.Driver"
        dataSource.url = "jdbc:postgresql://${host}:${port}/${name}"
        dataSource.username = user
        dataSource.password = pass
        dataSource.initialSize = 4
        return dataSource
    }
}