package edu.unifacef.aluguelDeCarros.service.impl;

import edu.unifacef.aluguelDeCarros.domain.Aluguel;
import edu.unifacef.aluguelDeCarros.domain.Carro;
import edu.unifacef.aluguelDeCarros.domain.Empresa;
import edu.unifacef.aluguelDeCarros.dto.AluguelCreateRequestDTO;
import edu.unifacef.aluguelDeCarros.dto.AluguelResponseDTO;
import edu.unifacef.aluguelDeCarros.dto.AluguelUpdateRequestDTO;
import edu.unifacef.aluguelDeCarros.enums.AluguelStatus;
import edu.unifacef.aluguelDeCarros.exception.DocumentNotFound;
import edu.unifacef.aluguelDeCarros.repository.AluguelRepository;
import edu.unifacef.aluguelDeCarros.repository.CarroRepository;
import edu.unifacef.aluguelDeCarros.repository.EmpresaRepository;
import edu.unifacef.aluguelDeCarros.service.AluguelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service("Aluguel01")
public class AluguelServiceImpl implements AluguelService {

    @Autowired
    private AluguelRepository repository;

    @Autowired
    private EmpresaRepository empresaRepository;

    @Autowired
    private CarroRepository carroRepository;

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
    public AluguelResponseDTO save(AluguelCreateRequestDTO aluguelCreateRequestDTO) {
        Aluguel newAluguel = aluguelCreateRequestDTO.transformToDocument();

        if(aluguelCreateRequestDTO.getCarroId() != null && !aluguelCreateRequestDTO.getCarroId().isBlank()) {
            Carro carro = this.carroRepository.findById(aluguelCreateRequestDTO.getCarroId()).orElseThrow(() -> new DocumentNotFound("Carro não encontrado"));
            newAluguel.setCarro(carro);
        }

        if(aluguelCreateRequestDTO.getEmpresaSaidaId() != null && !aluguelCreateRequestDTO.getEmpresaSaidaId().isBlank()) {
            Empresa empresaSaida = this.empresaRepository.findById(aluguelCreateRequestDTO.getEmpresaSaidaId()).orElseThrow(() -> new DocumentNotFound("Empresa não encontrado"));
            newAluguel.setEmpresaSaida(empresaSaida);
        }
        newAluguel.setStatus(AluguelStatus.EmAberto);
        newAluguel = this.repository.save(newAluguel);
        return new AluguelResponseDTO(newAluguel);
    }

    @Override
    public AluguelResponseDTO update(String id, AluguelUpdateRequestDTO aluguelUpdateRequestDTO) {
        if(id != null && !id.isBlank()){
            Aluguel cFound = this.repository.findById(id).orElseThrow(() -> new DocumentNotFound("Aluguel não encontrado"));
            Aluguel updateAluguel = aluguelUpdateRequestDTO.transformToDocument(cFound);
            updateAluguel.setId(cFound.getId());

            if(aluguelUpdateRequestDTO.getCarroId() != null && !aluguelUpdateRequestDTO.getCarroId().isBlank()) {
                Carro carro = this.carroRepository.findById(aluguelUpdateRequestDTO.getCarroId()).orElseThrow(() -> new DocumentNotFound("Carro não encontrado"));
                updateAluguel.setCarro(carro);
            }

            if(aluguelUpdateRequestDTO.getEmpresaSaidaId() != null && !aluguelUpdateRequestDTO.getEmpresaSaidaId().isBlank()) {
                Empresa empresaSaida = this.empresaRepository.findById(aluguelUpdateRequestDTO.getEmpresaSaidaId()).orElseThrow(() -> new DocumentNotFound("Empresa não encontrado"));
                updateAluguel.setEmpresaSaida(empresaSaida);
            }

            if(aluguelUpdateRequestDTO.getEmpresaChegadaId() != null && !aluguelUpdateRequestDTO.getEmpresaChegadaId().isBlank()) {
                Empresa empresaChegada = this.empresaRepository.findById(aluguelUpdateRequestDTO.getEmpresaChegadaId()).orElseThrow(() -> new DocumentNotFound("Empresa não encontrado"));
                updateAluguel.setEmpresaChegada(empresaChegada);
            }

            updateAluguel = this.repository.save(updateAluguel);
            return new AluguelResponseDTO(updateAluguel);
        } else {
            throw new IllegalArgumentException("É necessário informar o id do aluguel.");
        }


    }



    @Override
    public boolean delete(String id) {
        Aluguel a = this.repository.findById(id).orElseThrow(() -> new DocumentNotFound("Aluguel não encontrado"));
        this.repository.delete(a);
        return true;
    }
}
