package edu.unifacef.aluguelDeCarros.controller;

import edu.unifacef.aluguelDeCarros.dto.AluguelCreateRequestDTO;
import edu.unifacef.aluguelDeCarros.dto.AluguelResponseDTO;
import edu.unifacef.aluguelDeCarros.dto.AluguelUpdateRequestDTO;
import edu.unifacef.aluguelDeCarros.service.AluguelService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/alugueis")
@Api(value = "Aluguel",description = "End-points de aluguéis", consumes="application/json")
public class AluguelController {

    @Autowired
    @Qualifier("Aluguel01")
    private AluguelService service;

    @Operation(summary = "Retorna todos os aluguéis paginado")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Aluguéis encontrados",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Erro ao encontrar aluguéis",
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
    public ResponseEntity<Page<AluguelResponseDTO>> index(@RequestParam(value = "page", defaultValue = "0", required = false) int page,
                                                          @RequestParam(value = "size", defaultValue = "10", required = false) int size,
                                                          @RequestParam(value = "sort", defaultValue = "createdDate", required = false) String sort,
                                                          @RequestParam(value = "direction", defaultValue = "ASC", required = false) String direction){
        Page<AluguelResponseDTO> response = this.service.findAll(PageRequest.of(page, size, direction.equals("ASC") ? Sort.by(sort).ascending() : Sort.by(sort).descending()));
        return new ResponseEntity<Page<AluguelResponseDTO>>(response, HttpStatus.OK);
    }

    @Operation(summary = "Buscar um Aluguel pelo o id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "302", description = "Aluguel encontrado",
                    content = @Content),
            @ApiResponse(responseCode = "302", description = "Aluguel encontrado",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Erro ao encontrar Aluguel",
                    content = @Content) })
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", dataType = "String",  paramType = "path",
                    value = "Id do aluguel")
    })
    @GetMapping("/{id}")
    public ResponseEntity<AluguelResponseDTO> show(@PathVariable String id){
        AluguelResponseDTO response = this.service.findById(id);
        return new ResponseEntity<AluguelResponseDTO>(response, HttpStatus.FOUND);
    }

    @Operation(summary = "Salvar um novo Aluguel")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Aluguel foi salvo",
                    content = @Content(schema = @Schema(implementation = AluguelResponseDTO.class))),
            @ApiResponse(responseCode = "400", description = "Aluguel não foi salvo. Erro na requisição",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Aluguel não foi salvo. Erro interno",
                    content = @Content) })
    @PostMapping
    public ResponseEntity<AluguelResponseDTO> store(@Validated @RequestBody AluguelCreateRequestDTO request){
        AluguelResponseDTO response = this.service.save(request);
        return new ResponseEntity<AluguelResponseDTO>(response, HttpStatus.CREATED);
    }

    @Operation(summary = "Atualizar um Aluguel")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Aluguel foi salvo",
                    content = @Content(schema = @Schema(implementation = AluguelResponseDTO.class))),
            @ApiResponse(responseCode = "400", description = "Aluguel não foi salvo. Erro na requisição",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Erro ao encontrar Aluguel",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Aluguel não foi salvo. Erro interno",
                    content = @Content) })
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", dataType = "String", paramType = "path",
                    value = "Id do aluguel")
    })
    @PutMapping("/{id}")
    public ResponseEntity<AluguelResponseDTO> update(@PathVariable String id,
                                                     @Validated @RequestBody AluguelUpdateRequestDTO request){
        AluguelResponseDTO response = this.service.update(id, request);
        return new ResponseEntity<AluguelResponseDTO>(response, HttpStatus.OK);
    }

    @Operation(summary = "Deletar um Aluguel")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Aluguel foi deletado",
                    content = @Content),
            @ApiResponse(responseCode = "400", description = "Aluguel não foi deletado. Erro na requisição",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Erro ao encontrar Aluguel",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Aluguel não foi deletado. Erro interno",
                    content = @Content) })
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", dataType = "String",  paramType = "path",
                    value = "Id do aluguel")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable String id){
        boolean response = this.service.delete(id);
        return new ResponseEntity<Boolean>(response, HttpStatus.CREATED);
    }
}
