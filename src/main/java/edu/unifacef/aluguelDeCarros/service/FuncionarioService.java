package edu.unifacef.aluguelDeCarros.service;

import edu.unifacef.aluguelDeCarros.dto.FuncionarioRequestDTO;
import edu.unifacef.aluguelDeCarros.dto.FuncionarioResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.PathVariable;

public interface FuncionarioService {
    Page<FuncionarioResponseDTO> findAll(Pageable pageable);
    FuncionarioResponseDTO findById(String id);
    FuncionarioResponseDTO save(String empresaId, String id, FuncionarioRequestDTO funcionario);
    boolean delete(String id);

}
