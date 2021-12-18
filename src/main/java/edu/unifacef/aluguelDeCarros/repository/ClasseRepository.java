package edu.unifacef.aluguelDeCarros.repository;

import edu.unifacef.aluguelDeCarros.domain.Classe;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClasseRepository extends MongoRepository<Classe, String> {
}
