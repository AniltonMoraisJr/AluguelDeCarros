package edu.unifacef.aluguelDeCarros.service;

import edu.unifacef.aluguelDeCarros.dto.CarroRequestDTO;
import edu.unifacef.aluguelDeCarros.dto.CarroResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CarroService {
    Page<CarroResponseDTO> findAll(Pageable pageable);
    CarroResponseDTO findById(String id);
    CarroResponseDTO save(String id, CarroRequestDTO carroRequestDTO);
    boolean delete(String id);
}
