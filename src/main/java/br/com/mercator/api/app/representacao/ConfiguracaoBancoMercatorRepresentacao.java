package br.com.mercator.api.app.representacao;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

@Configuration
@EnableJpaRepositories(
        basePackages = "br.com.mercator.api.app.representacao.repositorio",
        transactionManagerRef = "transactionManagerRepresentacao",
        entityManagerFactoryRef ="entityManagerFactoryRepresentacao"
)
public class ConfiguracaoBancoMercatorRepresentacao {

    @Bean("dataSourceRepresentacao")
    @ConfigurationProperties(prefix = "mercator.representacao")
    public DataSource dataSource() {
        return DataSourceBuilder.create().type(HikariDataSource.class).build();
    }

    @Bean("entityManagerFactoryRepresentacao")
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(EntityManagerFactoryBuilder builder, @Qualifier("dataSourceRepresentacao") DataSource dataSource) {
        LocalContainerEntityManagerFactoryBean entityManagerFactory = builder
                .dataSource(dataSource)
                .packages("br.com.mercator.api.app.representacao.modelo")
                .persistenceUnit("MERCATOR_REPRESENTACAO").build();
        return entityManagerFactory;
    }

    @Bean("transactionManagerRepresentacao")
    public PlatformTransactionManager transactionManager(@Qualifier("entityManagerFactoryRepresentacao") EntityManagerFactory entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory);
    }

}
