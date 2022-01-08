package edu.unifacef.aluguelDeCarros.service.impl;

import edu.unifacef.aluguelDeCarros.domain.Aluguel;
import edu.unifacef.aluguelDeCarros.dto.AluguelRequestDTO;
import edu.unifacef.aluguelDeCarros.dto.AluguelResponseDTO;
import edu.unifacef.aluguelDeCarros.exception.DocumentNotFound;
import edu.unifacef.aluguelDeCarros.repository.AluguelRepository;
import edu.unifacef.aluguelDeCarros.service.AluguelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AluguelServiceImpl implements AluguelService {

    @Autowired
    private AluguelRepository repository;

    @Override
    public Page<AluguelResponseDTO> findAll(Pageable pageable) {
        Page<Aluguel> classePage = this.repository.findAll(pageable);
        List<AluguelResponseDTO> listDtos = classePage.getContent().stream().map(a -> {
            AluguelResponseDTO dto = new AluguelResponseDTO(a);
            return dto;
        }).collect(Collectors.toList());
        return new PageImpl<AluguelResponseDTO>(listDtos, pageable, classePage.getTotalElements());
    }

    @Override
    public AluguelResponseDTO findById(String id) {
        Aluguel a = this.repository.findById(id).orElseThrow(() -> new DocumentNotFound("Aluguel não encontrada!"));
        return new AluguelResponseDTO(a);
    }

    @Override
    public AluguelResponseDTO save(String id, AluguelRequestDTO aluguelRequestDTO) {
        Aluguel newAluguel = aluguelRequestDTO.transformToDocument();
        if(id != null && !id.isBlank()){
            Aluguel cFound = this.repository.findById(id).orElseThrow(() -> new DocumentNotFound("Aluguel não encontrado"));
            newAluguel.setId(cFound.getId());
        }
        newAluguel = this.repository.save(newAluguel);
        return new AluguelResponseDTO(newAluguel);
    }

    @Override
    public boolean delete(String id) {
        Aluguel a = this.repository.findById(id).orElseThrow(() -> new DocumentNotFound("Aluguel não encontrado"));
        this.repository.delete(a);
        return true;
    }
}
