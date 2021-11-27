package edu.unifacef.aluguelDeCarros.controller;

import edu.unifacef.aluguelDeCarros.dto.ClienteRequestDTO;
import edu.unifacef.aluguelDeCarros.dto.ClienteResponseDTO;
import edu.unifacef.aluguelDeCarros.service.ClienteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private ClienteService service;

    @Operation(summary = "Retorna todos os clientes paginado")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Clientes encontrados",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Erro ao encontrar clientes",
                    content = @Content) })
    @GetMapping
    public ResponseEntity<Page<ClienteResponseDTO>> index(Pageable pageable){
        Page<ClienteResponseDTO> response = this.service.findAll(pageable);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Operation(summary = "Buscar um cliente pelo o id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "302", description = "Cliente encontrado",
                    content = @Content),
            @ApiResponse(responseCode = "302", description = "Cliente encontrado",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Erro ao encontrar cliente",
                    content = @Content) })
    @GetMapping("/{id}")
    public ResponseEntity<ClienteResponseDTO> show(@PathVariable String id){
        ClienteResponseDTO response = this.service.findById(id);
        return new ResponseEntity<>(response, HttpStatus.FOUND);
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
        return new ResponseEntity<>(response, HttpStatus.CREATED);
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
    @PutMapping("/{id}")
    public ResponseEntity<ClienteResponseDTO> update(@PathVariable String id, @Validated @RequestBody ClienteRequestDTO request){
        ClienteResponseDTO response = this.service.save(id, request);
        return new ResponseEntity<>(response, HttpStatus.OK);
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
    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable String id){
        boolean response = this.service.delete(id);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
