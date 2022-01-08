package edu.unifacef.aluguelDeCarros.service;

import edu.unifacef.aluguelDeCarros.dto.EmpresaResponseDTO;
import edu.unifacef.aluguelDeCarros.dto.EmpresaRequestDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface EmpresaService {
    Page<EmpresaResponseDTO> findAll(Pageable pageable);
    EmpresaResponseDTO findById(String id);
    EmpresaResponseDTO save(String id, EmpresaRequestDTO empresaRequestDTO);
    boolean delete(String id);
}
