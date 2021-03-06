package edu.unifacef.aluguelDeCarros.controller;

import edu.unifacef.aluguelDeCarros.dto.ClienteRequestDTO;
import edu.unifacef.aluguelDeCarros.dto.ClienteResponseDTO;
import edu.unifacef.aluguelDeCarros.service.ClienteService;
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
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/clientes")
@Api(value = "Cliente",description = "End-points de clientes", consumes="application/json")
public class ClienteController {

    @Autowired
    private ClienteService service;

    @Operation(summary = "Retorna todos os clientes paginado")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Clientes encontrados",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Erro ao encontrar clientes",
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
    public ResponseEntity<Page<ClienteResponseDTO>> index(@RequestParam(value = "page", defaultValue = "0", required = false) int page,
                                                          @RequestParam(value = "size", defaultValue = "10", required = false) int size,
                                                          @RequestParam(value = "sort", defaultValue = "createdDate", required = false) String sort,
                                                          @RequestParam(value = "direction", defaultValue = "ASC", required = false) String direction){
        Page<ClienteResponseDTO> response = this.service.findAll(PageRequest.of(page, size, direction.equals("ASC") ? Sort.by(sort).ascending() : Sort.by(sort).descending()));
        return new ResponseEntity<Page<ClienteResponseDTO>>(response, HttpStatus.OK);
    }

    @Operation(summary = "Buscar um cliente pelo o id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "302", description = "Cliente encontrado",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Erro ao encontrar cliente",
                    content = @Content) })
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", dataType = "String", dataTypeClass = java.lang.String.class,  paramType = "path",
                    value = "Id do cliente")
    })
    @GetMapping("/{id}")
    public ResponseEntity<ClienteResponseDTO> show(@PathVariable String id){
        ClienteResponseDTO response = this.service.findById(id);
        return new ResponseEntity<ClienteResponseDTO>(response, HttpStatus.FOUND);
    }

    @Operation(summary = "Salvar um novo cliente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Cliente foi salvo",
                    content = @Content(schema = @Schema(implementation = ClienteResponseDTO.class))),
            @ApiResponse(responseCode = "400", description = "Cliente não foi salvo. Erro na requisição",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Cliente não foi salvo. Erro interno",
                    content = @Content) })
    @PostMapping
    public ResponseEntity<ClienteResponseDTO> store(@Validated @RequestBody ClienteRequestDTO request){
        ClienteResponseDTO response = this.service.save(null, request);
        return new ResponseEntity<ClienteResponseDTO>(response, HttpStatus.CREATED);
    }

    @Operation(summary = "Atualizar um cliente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Cliente foi salvo",
                    content = @Content(schema = @Schema(implementation = ClienteResponseDTO.class))),
            @ApiResponse(responseCode = "400", description = "Cliente não foi salvo. Erro na requisição",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Erro ao encontrar cliente",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Cliente não foi salvo. Erro interno",
                    content = @Content) })
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", dataType = "String", dataTypeClass = java.lang.String.class, paramType = "path",
                    value = "Id do cliente")
    })
    @PutMapping("/{id}")
    public ResponseEntity<ClienteResponseDTO> update(@PathVariable String id, @Validated @RequestBody ClienteRequestDTO request){
        ClienteResponseDTO response = this.service.save(id, request);
        return new ResponseEntity<ClienteResponseDTO>(response, HttpStatus.OK);
    }

    @Operation(summary = "Deletar um cliente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Cliente foi deletado",
                    content = @Content),
            @ApiResponse(responseCode = "400", description = "Cliente não foi deletado. Erro na requisição",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Erro ao encontrar cliente",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Cliente não foi deletado. Erro interno",
                    content = @Content) })
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", dataType = "String",  dataTypeClass = java.lang.String.class,  paramType = "path",
                    value = "Id do cliente")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable String id){
        boolean response = this.service.delete(id);
        return new ResponseEntity<Boolean>(response, HttpStatus.CREATED);
    }
}
