package edu.unifacef.aluguelDeCarros.service;

import edu.unifacef.aluguelDeCarros.dto.AluguelRequestDTO;
import edu.unifacef.aluguelDeCarros.dto.AluguelResponseDTO;
import edu.unifacef.aluguelDeCarros.dto.CarroRequestDTO;
import edu.unifacef.aluguelDeCarros.dto.CarroResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface AluguelService {
    Page<AluguelResponseDTO> findAll(Pageable pageable);
    AluguelResponseDTO findById(String id);
    AluguelResponseDTO save(String id, AluguelRequestDTO aluguelRequestDTO);
    boolean delete(String id);
}
