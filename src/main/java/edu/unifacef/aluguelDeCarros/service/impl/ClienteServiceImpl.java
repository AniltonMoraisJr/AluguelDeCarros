package edu.unifacef.aluguelDeCarros.service.impl;

import edu.unifacef.aluguelDeCarros.domain.Cliente;
import edu.unifacef.aluguelDeCarros.dto.ClienteRequestDTO;
import edu.unifacef.aluguelDeCarros.dto.ClienteResponseDTO;
import edu.unifacef.aluguelDeCarros.exception.DocumentNotFound;
import edu.unifacef.aluguelDeCarros.repository.ClienteRepository;
import edu.unifacef.aluguelDeCarros.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClienteServiceImpl implements ClienteService {

    @Autowired
    private ClienteRepository repository;

    @Override
    public Page<ClienteResponseDTO> findAll(Pageable pageable) {
        Page<Cliente> clientePage = this.repository.findAll(pageable);
        List<ClienteResponseDTO> listDtos = clientePage.getContent().stream().map(c -> {
            ClienteResponseDTO dto = new ClienteResponseDTO(c);
            return dto;
        }).collect(Collectors.toList());
        return new PageImpl<ClienteResponseDTO>(listDtos, pageable, clientePage.getTotalElements());
    }

    @Override
    public ClienteResponseDTO findById(String id) {
        Cliente c = this.repository.findById(id).orElseThrow(() -> new DocumentNotFound("Cliente não encontrado"));
        return new ClienteResponseDTO(c);
    }

    @Override
    public ClienteResponseDTO save(String id, ClienteRequestDTO cliente) {
        Cliente newCliente = cliente.transformToDocument();
        // Se for uma atualização, busco o cliente e seto o id
        if(id != null && !id.isBlank()){
            Cliente cFound = this.repository.findById(id).orElseThrow(() -> new DocumentNotFound("Cliente não encontrado"));
            newCliente.setId(cFound.getId());
        }
        newCliente = this.repository.save(newCliente);
        return new ClienteResponseDTO(newCliente);
    }

    @Override
    public boolean delete(String id) {
        Cliente c = this.repository.findById(id).orElseThrow(() -> new DocumentNotFound("Cliente não encontrado"));
        this.repository.delete(c);
        return true;
    }
}
