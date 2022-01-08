package edu.unifacef.aluguelDeCarros.repository;

import edu.unifacef.aluguelDeCarros.domain.Empresa;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface EmpresaRepository extends MongoRepository<Empresa, String> {
}
