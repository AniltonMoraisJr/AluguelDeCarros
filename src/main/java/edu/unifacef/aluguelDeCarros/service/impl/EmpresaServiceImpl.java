package edu.unifacef.aluguelDeCarros.service.impl;

import edu.unifacef.aluguelDeCarros.domain.Empresa;
import edu.unifacef.aluguelDeCarros.dto.EmpresaRequestDTO;
import edu.unifacef.aluguelDeCarros.dto.EmpresaResponseDTO;
import edu.unifacef.aluguelDeCarros.exception.DocumentNotFound;
import edu.unifacef.aluguelDeCarros.repository.EmpresaRepository;
import edu.unifacef.aluguelDeCarros.service.EmpresaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmpresaServiceImpl implements EmpresaService {

    @Autowired
    private EmpresaRepository repository;

    @Override
    public Page<EmpresaResponseDTO> findAll(Pageable pageable) {
        Page<Empresa> empresaPage = this.repository.findAll(pageable);
        List<EmpresaResponseDTO> listDtos = empresaPage.getContent().stream().map(e -> {
            EmpresaResponseDTO dto = new EmpresaResponseDTO(e);
            return dto;
        }).collect(Collectors.toList());
        return new PageImpl<EmpresaResponseDTO>(listDtos, pageable, empresaPage.getTotalElements());
    }

    @Override
    public EmpresaResponseDTO findById(String id) {
        Empresa e = this.repository.findById(id).orElseThrow(() -> new DocumentNotFound("Empresa não encontrada!"));
        return new EmpresaResponseDTO(e);
    }

    @Override
    public EmpresaResponseDTO save(String id, EmpresaRequestDTO empresaRequestDTO) {
        Empresa newEmpresa = empresaRequestDTO.transformToDocument();

        if(id != null && !id.isBlank()){
            Empresa cFound = this.repository.findById(id).orElseThrow(() -> new DocumentNotFound("Empresa não encontrado"));
            newEmpresa.setId(cFound.getId());
        }
        newEmpresa = this.repository.save(newEmpresa);
        return new EmpresaResponseDTO(newEmpresa);
    }

    @Override
    public boolean delete(String id) {
        Empresa c = this.repository.findById(id).orElseThrow(() -> new DocumentNotFound("Empresa não encontrado"));
        this.repository.delete(c);
        return true;
    }
}
