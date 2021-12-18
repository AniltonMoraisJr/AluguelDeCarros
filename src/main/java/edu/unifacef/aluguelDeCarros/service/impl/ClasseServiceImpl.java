package edu.unifacef.aluguelDeCarros.service.impl;

import edu.unifacef.aluguelDeCarros.domain.Classe;
import edu.unifacef.aluguelDeCarros.domain.Cliente;
import edu.unifacef.aluguelDeCarros.dto.ClasseRequestDTO;
import edu.unifacef.aluguelDeCarros.dto.ClasseResponseDTO;
import edu.unifacef.aluguelDeCarros.dto.ClienteResponseDTO;
import edu.unifacef.aluguelDeCarros.exception.DocumentNotFound;
import edu.unifacef.aluguelDeCarros.repository.ClasseRepository;
import edu.unifacef.aluguelDeCarros.service.ClasseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClasseServiceImpl implements ClasseService {

    @Autowired
    private ClasseRepository repository;

    @Override
    public Page<ClasseResponseDTO> findAll(Pageable pageable) {
        Page<Classe> classePage = this.repository.findAll(pageable);
        List<ClasseResponseDTO> listDtos = classePage.getContent().stream().map(c -> {
            ClasseResponseDTO dto = new ClasseResponseDTO(c);
            return dto;
        }).collect(Collectors.toList());
        return new PageImpl<ClasseResponseDTO>(listDtos, pageable, classePage.getTotalElements());
    }

    @Override
    public ClasseResponseDTO findById(String id) {
        Classe c = this.repository.findById(id).orElseThrow(() -> new DocumentNotFound("Classe não encontrada!"));
        return new ClasseResponseDTO(c);
    }

    @Override
    public ClasseResponseDTO save(String id, ClasseRequestDTO classeRequestDTO) {
        Classe newClasse = classeRequestDTO.transformToDocument();
        // Se for uma atualização, busco o cliente e seto o id
        if(id != null && !id.isBlank()){
            Classe cFound = this.repository.findById(id).orElseThrow(() -> new DocumentNotFound("Classe não encontrado"));
            newClasse.setIdClasse(cFound.getIdClasse());
        }
        newClasse = this.repository.save(newClasse);
        return new ClasseResponseDTO(newClasse);
    }

    @Override
    public boolean delete(String id) {
        Classe c = this.repository.findById(id).orElseThrow(() -> new DocumentNotFound("Classe não encontrado"));
        this.repository.delete(c);
        return true;
    }
}
