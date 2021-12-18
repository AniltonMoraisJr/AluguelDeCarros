package edu.unifacef.aluguelDeCarros.service.impl;

import edu.unifacef.aluguelDeCarros.domain.Carro;
import edu.unifacef.aluguelDeCarros.dto.CarroRequestDTO;
import edu.unifacef.aluguelDeCarros.dto.CarroResponseDTO;
import edu.unifacef.aluguelDeCarros.exception.DocumentNotFound;
import edu.unifacef.aluguelDeCarros.repository.CarroRepository;
import edu.unifacef.aluguelDeCarros.service.CarroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CarroServiceImpl implements CarroService {

    @Autowired
    private CarroRepository repository;

    @Override
    public Page<CarroResponseDTO> findAll(Pageable pageable) {
        Page<Carro> classePage = this.repository.findAll(pageable);
        List<CarroResponseDTO> listDtos = classePage.getContent().stream().map(c -> {
            CarroResponseDTO dto = new CarroResponseDTO(c);
            return dto;
        }).collect(Collectors.toList());
        return new PageImpl<CarroResponseDTO>(listDtos, pageable, classePage.getTotalElements());
    }

    @Override
    public CarroResponseDTO findById(String id) {
        Carro c = this.repository.findById(id).orElseThrow(() -> new DocumentNotFound("Carro não encontrada!"));
        return new CarroResponseDTO(c);
    }

    @Override
    public CarroResponseDTO save(String id, CarroRequestDTO carroRequestDTO) {
        Carro newCarro = carroRequestDTO.transformToDocument();
        if(id != null && !id.isBlank()){
            Carro cFound = this.repository.findById(id).orElseThrow(() -> new DocumentNotFound("Carro não encontrado"));
            newCarro.setIdCarro(cFound.getIdCarro());
        }
        newCarro = this.repository.save(newCarro);
        return new CarroResponseDTO(newCarro);
    }

    @Override
    public boolean delete(String id) {
        Carro c = this.repository.findById(id).orElseThrow(() -> new DocumentNotFound("Carro não encontrado"));
        this.repository.delete(c);
        return true;
    }
}
