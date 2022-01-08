package edu.unifacef.aluguelDeCarros.repository;

import edu.unifacef.aluguelDeCarros.domain.Aluguel;
import edu.unifacef.aluguelDeCarros.domain.Carro;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AluguelRepository  extends MongoRepository<Aluguel, String> {
}
