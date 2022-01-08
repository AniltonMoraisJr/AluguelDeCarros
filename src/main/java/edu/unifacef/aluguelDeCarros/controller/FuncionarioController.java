package edu.unifacef.aluguelDeCarros.controller;

import edu.unifacef.aluguelDeCarros.dto.ClienteResponseDTO;
import edu.unifacef.aluguelDeCarros.dto.FuncionarioRequestDTO;
import edu.unifacef.aluguelDeCarros.dto.FuncionarioResponseDTO;
import edu.unifacef.aluguelDeCarros.service.FuncionarioService;
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
@RequestMapping("/empresas/{empresaId}/funcionarios")
@Api(value = "Funcionario",description = "End-points de funcionarios", consumes="application/json")
public class FuncionarioController {

    @Autowired
    private FuncionarioService service;

    @Operation(summary = "Retorna todos os funcionarios paginado")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Funcionarios encontrados",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Erro ao encontrar funcionarios",
                    content = @Content) })
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", dataType = "int", example = "0", defaultValue = "0", paramType = "query",
                    value = "Numero da página a ser buscada (0..N)"),
            @ApiImplicitParam(name = "size", dataType = "int", example = "10", defaultValue = "10", paramType = "query",
                    value = "Quantidade de registros por página."),
            @ApiImplicitParam(name = "sort", paramType = "query", example = "createdDate",
                    value = "Campo a ser ordenado."),
            @ApiImplicitParam(name = "direction", paramType = "query",
                    value = "Direção da ordenação ASC|DESC.")
    })
    @GetMapping
    public ResponseEntity<Page<FuncionarioResponseDTO>> index(@PathVariable String empresaId,
                                                              @RequestParam(value = "page", defaultValue = "0", required = false) int page,
                                                              @RequestParam(value = "size", defaultValue = "10", required = false) int size,
                                                              @RequestParam(value = "sort", defaultValue = "createdDate", required = false) String sort,
                                                              @RequestParam(value = "direction", defaultValue = "ASC", required = false) String direction){
        Page<FuncionarioResponseDTO> response = this.service.findAll(PageRequest.of(page, size, direction.equals("ASC") ? Sort.by(sort).ascending() : Sort.by(sort).descending()));
        return new ResponseEntity<Page<FuncionarioResponseDTO>>(response, HttpStatus.OK);
    }

    @Operation(summary = "Buscar um Funcionario pelo o id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "302", description = "Funcionario encontrado",
                    content = @Content),
            @ApiResponse(responseCode = "302", description = "Funcionario encontrado",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Erro ao encontrar Funcionario",
                    content = @Content) })
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", dataType = "String",  paramType = "path",
                    value = "Id do funcionário")
    })
    @GetMapping("/{id}")
    public ResponseEntity<FuncionarioResponseDTO> show(@PathVariable String empresaId,@PathVariable String id){
        FuncionarioResponseDTO response = this.service.findById(id);
        return new ResponseEntity<FuncionarioResponseDTO>(response, HttpStatus.FOUND);
    }

    @Operation(summary = "Salvar um novo Funcionario")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Funcionario foi salvo",
                    content = @Content(schema = @Schema(implementation = FuncionarioResponseDTO.class))),
            @ApiResponse(responseCode = "400", description = "Funcionario não foi salvo. Erro na requisição",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Funcionario não foi salvo. Erro interno",
                    content = @Content) })
    @PostMapping
    public ResponseEntity<FuncionarioResponseDTO> store(@PathVariable String empresaId,
                                                        @Validated @RequestBody FuncionarioRequestDTO request){
        FuncionarioResponseDTO response = this.service.save(empresaId, null, request);
        return new ResponseEntity<FuncionarioResponseDTO>(response, HttpStatus.CREATED);
    }

    @Operation(summary = "Atualizar um Funcionario")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Funcionario foi salvo",
                    content = @Content(schema = @Schema(implementation = FuncionarioResponseDTO.class))),
            @ApiResponse(responseCode = "400", description = "Funcionario não foi salvo. Erro na requisição",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Erro ao encontrar Funcionario",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Funcionario não foi salvo. Erro interno",
                    content = @Content) })
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", dataType = "String", paramType = "path",
                    value = "Id do funcionário")
    })
    @PutMapping("/{id}")
    public ResponseEntity<FuncionarioResponseDTO> update(@PathVariable String empresaId,
                                                         @PathVariable String id,
                                                         @Validated @RequestBody FuncionarioRequestDTO request){
        FuncionarioResponseDTO response = this.service.save(empresaId, id, request);
        return new ResponseEntity<FuncionarioResponseDTO>(response, HttpStatus.OK);
    }

    @Operation(summary = "Deletar um Funcionario")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Funcionario foi deletado",
                    content = @Content),
            @ApiResponse(responseCode = "400", description = "Funcionario não foi deletado. Erro na requisição",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Erro ao encontrar Funcionario",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Funcionario não foi deletado. Erro interno",
                    content = @Content) })
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", dataType = "String",  paramType = "path",
                    value = "Id do funcionário")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable String id){
        boolean response = this.service.delete(id);
        return new ResponseEntity<Boolean>(response, HttpStatus.CREATED);
    }
}
