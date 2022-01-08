package edu.unifacef.aluguelDeCarros.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.EnableMongoAuditing;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableMongoAuditing
@EnableMongoRepositories(basePackages = {"edu.unifacef.aluguelDeCarros.repository"})
public class MongoConfig {
}
