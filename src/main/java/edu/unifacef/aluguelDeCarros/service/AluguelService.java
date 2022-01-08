package edu.unifacef.aluguelDeCarros.service;

import edu.unifacef.aluguelDeCarros.dto.AluguelCreateRequestDTO;
import edu.unifacef.aluguelDeCarros.dto.AluguelResponseDTO;
import edu.unifacef.aluguelDeCarros.dto.AluguelUpdateRequestDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface AluguelService {
    Page<AluguelResponseDTO> findAll(Pageable pageable);
    AluguelResponseDTO findById(String id);
    AluguelResponseDTO save(AluguelCreateRequestDTO aluguelCreateRequestDTO);
    AluguelResponseDTO update(String id, AluguelUpdateRequestDTO aluguelUpdateRequestDTO);
    boolean delete(String id);
}
