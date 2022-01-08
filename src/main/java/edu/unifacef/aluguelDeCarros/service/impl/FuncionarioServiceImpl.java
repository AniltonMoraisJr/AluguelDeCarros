package edu.unifacef.aluguelDeCarros.service.impl;

import edu.unifacef.aluguelDeCarros.domain.Cliente;
import edu.unifacef.aluguelDeCarros.domain.Funcionario;
import edu.unifacef.aluguelDeCarros.dto.ClienteRequestDTO;
import edu.unifacef.aluguelDeCarros.dto.ClienteResponseDTO;
import edu.unifacef.aluguelDeCarros.dto.FuncionarioRequestDTO;
import edu.unifacef.aluguelDeCarros.dto.FuncionarioResponseDTO;
import edu.unifacef.aluguelDeCarros.exception.DocumentNotFound;
import edu.unifacef.aluguelDeCarros.repository.ClienteRepository;
import edu.unifacef.aluguelDeCarros.repository.FuncionarioRepository;
import edu.unifacef.aluguelDeCarros.service.FuncionarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FuncionarioServiceImpl implements FuncionarioService {

    @Autowired
    private FuncionarioRepository repository;

    @Override
    public Page<FuncionarioResponseDTO> findAll(Pageable pageable) {
        Page<Funcionario> funcionarioPage = this.repository.findAll(pageable);
        List<FuncionarioResponseDTO> listDtos = funcionarioPage.getContent().stream().map(c -> new FuncionarioResponseDTO(c)).collect(Collectors.toList());
        return new PageImpl<FuncionarioResponseDTO>(listDtos, pageable, funcionarioPage.getTotalElements());
    }

    @Override
    public FuncionarioResponseDTO findById(String id) {
        Funcionario f = this.repository.findById(id).orElseThrow(() -> new DocumentNotFound("Funcionario não encontrado"));
        return new FuncionarioResponseDTO(f);
    }

    @Override
    public FuncionarioResponseDTO save(String empresaId, String id, FuncionarioRequestDTO funcionario) {
        Funcionario newFuncionario = funcionario.transformToDocument(empresaId);

        // Se for uma atualização, busco o Funcionario e seto o id
        if(id != null && !id.isBlank()){
            Funcionario cFound = this.repository.findById(id).orElseThrow(() -> new DocumentNotFound("Funcionario não encontrado"));
            newFuncionario.setId(cFound.getId());
        }
        newFuncionario = this.repository.save(newFuncionario);
        return new FuncionarioResponseDTO(newFuncionario);
    }

    @Override
    public boolean delete(String id) {
        Funcionario f = this.repository.findById(id).orElseThrow(() -> new DocumentNotFound("Funcionario não encontrado"));
        this.repository.delete(f);
        return true;
    }
}
