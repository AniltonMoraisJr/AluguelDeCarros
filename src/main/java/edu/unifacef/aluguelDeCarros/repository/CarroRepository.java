package edu.unifacef.aluguelDeCarros.repository;

import edu.unifacef.aluguelDeCarros.domain.Carro;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CarroRepository  extends MongoRepository<Carro, String> {
}
