package edu.unifacef.aluguelDeCarros.repository;

import edu.unifacef.aluguelDeCarros.domain.Cliente;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends MongoRepository<Cliente, String> {
}
