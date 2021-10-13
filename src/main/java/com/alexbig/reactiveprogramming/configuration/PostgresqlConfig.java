package com.alexbig.reactiveprogramming.configuration;

import io.r2dbc.spi.ConnectionFactories;
import io.r2dbc.spi.ConnectionFactory;
import io.r2dbc.spi.ConnectionFactoryOptions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.r2dbc.config.AbstractR2dbcConfiguration;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;


@Configuration
@EnableR2dbcRepositories("com.alexbig.reactiveprogramming.repository")
public class PostgresqlConfig extends AbstractR2dbcConfiguration {

    @Override
    @Bean
    public ConnectionFactory connectionFactory() {
        return ConnectionFactories.get(ConnectionFactoryOptions.builder()
                .option(ConnectionFactoryOptions.DRIVER, "postgresql")
                .option(ConnectionFactoryOptions.HOST, "localhost")
                .option(ConnectionFactoryOptions.PORT, 5432)
                .option(ConnectionFactoryOptions.USER, "postgres")
                .option(ConnectionFactoryOptions.PASSWORD, "postgres")
                .option(ConnectionFactoryOptions.DATABASE, "booksdb")
                .build());
    }

}
