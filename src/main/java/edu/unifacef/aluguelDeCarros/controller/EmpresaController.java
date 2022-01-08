package edu.unifacef.aluguelDeCarros.controller;

import edu.unifacef.aluguelDeCarros.dto.*;
import edu.unifacef.aluguelDeCarros.service.EmpresaService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/empresas")
public class EmpresaController {

    @Autowired
    private EmpresaService service;

    @Operation(summary = "Retorna todas as empresas paginado")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Empresas encontradas",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Erro ao encontrar empresas",
                    content = @Content) })
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", dataType = "Integer", dataTypeClass = java.lang.Integer.class, example = "0", defaultValue = "0", paramType = "query",
                    value = "Numero da página a ser buscada (0..N)"),
            @ApiImplicitParam(name = "size", dataType = "Integer", dataTypeClass = java.lang.Integer.class, example = "10", defaultValue = "10", paramType = "query",
                    value = "Quantidade de registros por página."),
            @ApiImplicitParam(name = "sort", dataType = "String", dataTypeClass = java.lang.String.class, paramType = "query", example = "createdDate",
                    value = "Campo a ser ordenado."),
            @ApiImplicitParam(name = "direction", dataType = "String", dataTypeClass = java.lang.String.class, paramType = "query",
                    value = "Direção da ordenação ASC|DESC.")
    })
    @GetMapping
    public ResponseEntity<Page<EmpresaResponseDTO>> index(@RequestParam(value = "page", defaultValue = "0", required = false) int page,
                                                          @RequestParam(value = "size", defaultValue = "10", required = false) int size,
                                                          @RequestParam(value = "sort", defaultValue = "createdDate", required = false) String sort,
                                                          @RequestParam(value = "direction", defaultValue = "ASC", required = false) String direction){
        Page<EmpresaResponseDTO> response = this.service.findAll(PageRequest.of(page, size, direction.equals("ASC") ? Sort.by(sort).ascending() : Sort.by(sort).descending()));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Operation(summary = "Buscar uma empresa pelo o id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Empresa Encontrada",
                    content = @Content),
            @ApiResponse(responseCode = "302", description = "Empresa Encontrada",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Erro ao encontrar empresa",
                    content = @Content) })
    @GetMapping("/{id}")
    public ResponseEntity<EmpresaResponseDTO> show(@PathVariable String id){
        EmpresaResponseDTO response = this.service.findById(id);
        return new ResponseEntity<>(response, HttpStatus.FOUND);
    }

    @Operation(summary = "Salvar uma nova empresa")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Empresa foi salva",
                    content = @Content(schema = @Schema(implementation = EmpresaResponseDTO.class))),
            @ApiResponse(responseCode = "400", description = "Empresa não foi salva. Erro na requisição",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Empresa não foi salva. Erro interno",
                    content = @Content) })
    @PostMapping
    public ResponseEntity<EmpresaResponseDTO> store(@Validated @RequestBody EmpresaRequestDTO request){
        EmpresaResponseDTO response = this.service.save(null, request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @Operation(summary = "Atualizar empresa")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Empresa foi salva",
                    content = @Content(schema = @Schema(implementation = EmpresaResponseDTO.class))),
            @ApiResponse(responseCode = "400", description = "Empresa não foi salva. Erro na requisição",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Erro ao encontrar empresa",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Empresa não foi salva. Erro interno",
                    content = @Content) })
    @PutMapping("/{id}")
    public ResponseEntity<EmpresaResponseDTO> update(@PathVariable String id, @Validated @RequestBody EmpresaRequestDTO request){
        EmpresaResponseDTO response = this.service.save(id, request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Operation(summary = "Deletar empresa")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Empresa foi deletada",
                    content = @Content),
            @ApiResponse(responseCode = "400", description = "Empresa não foi deletada. Erro na requisição",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Erro ao encontrar empresa",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Empresa não foi deletada. Erro interno",
                    content = @Content) })
    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable String id){
        boolean response = this.service.delete(id);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

}
