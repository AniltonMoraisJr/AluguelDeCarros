package edu.unifacef.aluguelDeCarros.service;

import edu.unifacef.aluguelDeCarros.domain.Cliente;
import edu.unifacef.aluguelDeCarros.dto.ClienteRequestDTO;
import edu.unifacef.aluguelDeCarros.dto.ClienteResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ClienteService {
    Page<ClienteResponseDTO> findAll(Pageable pageable);
    ClienteResponseDTO findById(String id);
    ClienteResponseDTO save(String id, ClienteRequestDTO cliente);
    boolean delete(String id);

}
