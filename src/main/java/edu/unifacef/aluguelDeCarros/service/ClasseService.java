package edu.unifacef.aluguelDeCarros.service;

import edu.unifacef.aluguelDeCarros.dto.ClasseRequestDTO;
import edu.unifacef.aluguelDeCarros.dto.ClasseResponseDTO;
import edu.unifacef.aluguelDeCarros.dto.ClienteRequestDTO;
import edu.unifacef.aluguelDeCarros.dto.ClienteResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ClasseService {
    Page<ClasseResponseDTO> findAll(Pageable pageable);
    ClasseResponseDTO findById(String id);
    ClasseResponseDTO save(String id, ClasseRequestDTO classeRequestDTO);
    boolean delete(String id);
}
