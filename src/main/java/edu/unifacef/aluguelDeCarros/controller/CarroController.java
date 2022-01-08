package edu.unifacef.aluguelDeCarros.controller;

import edu.unifacef.aluguelDeCarros.dto.CarroRequestDTO;
import edu.unifacef.aluguelDeCarros.dto.CarroResponseDTO;
import edu.unifacef.aluguelDeCarros.service.CarroService;
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
@RequestMapping("/carros")
@Api(value = "Carros",description = "End-points de carros", consumes="application/json")
public class CarroController {

    @Autowired
    private CarroService service;


    @Operation(summary = "Retorna todos os carros paginado")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Carros encontrados",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Erro ao encontrar carros",
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
    public ResponseEntity<Page<CarroResponseDTO>> index(@RequestParam(value = "page", defaultValue = "0", required = false) int page,
                                                        @RequestParam(value = "size", defaultValue = "10", required = false) int size,
                                                        @RequestParam(value = "sort", defaultValue = "createdDate", required = false) String sort,
                                                        @RequestParam(value = "direction", defaultValue = "ASC", required = false) String direction){
        Page<CarroResponseDTO> response = this.service.findAll(PageRequest.of(page, size, direction.equals("ASC") ? Sort.by(sort).ascending() : Sort.by(sort).descending()));
        return new ResponseEntity<Page<CarroResponseDTO>>(response, HttpStatus.OK);
    }

    @Operation(summary = "Buscar um carro pelo o id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "302", description = "Carro encontrado",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Erro ao encontrar carro",
                    content = @Content) })
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", dataType = "String", dataTypeClass = java.lang.String.class, paramType = "path",
                    value = "Id do carro")
    })
    @GetMapping("/{id}")
    public ResponseEntity<CarroResponseDTO> show(@PathVariable String id){
        CarroResponseDTO response = this.service.findById(id);
        return new ResponseEntity<CarroResponseDTO>(response, HttpStatus.FOUND);
    }

    @Operation(summary = "Salvar um novo carro")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Carro foi salvo",
                    content = @Content(schema = @Schema(implementation = CarroResponseDTO.class))),
            @ApiResponse(responseCode = "400", description = "Carro não foi salvo. Erro na requisição",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Carro não foi salvo. Erro interno",
                    content = @Content) })
    @PostMapping
    public ResponseEntity<CarroResponseDTO> store(@Validated @RequestBody CarroRequestDTO request){
        CarroResponseDTO response = this.service.save(null, request);
        return new ResponseEntity<CarroResponseDTO>(response, HttpStatus.CREATED);
    }

    @Operation(summary = "Atualizar um carro")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Carro foi salvo",
                    content = @Content(schema = @Schema(implementation = CarroResponseDTO.class))),
            @ApiResponse(responseCode = "400", description = "Carro não foi salvo. Erro na requisição",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Erro ao encontrar carro",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Carro não foi salvo. Erro interno",
                    content = @Content) })
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", dataType = "String", dataTypeClass = java.lang.String.class,  paramType = "path",
                    value = "Id do carro")
    })
    @PutMapping("/{id}")
    public ResponseEntity<CarroResponseDTO> update(@PathVariable String id, @Validated @RequestBody CarroRequestDTO request){
        CarroResponseDTO response = this.service.save(id, request);
        return new ResponseEntity<CarroResponseDTO>(response, HttpStatus.OK);
    }

    @Operation(summary = "Deletar um carro")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Carro foi deletado",
                    content = @Content),
            @ApiResponse(responseCode = "400", description = "Carro não foi deletado. Erro na requisição",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Erro ao encontrar carro",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Carro não foi deletado. Erro interno",
                    content = @Content) })
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", dataType = "String", dataTypeClass = java.lang.String.class,  paramType = "path",
                    value = "Id do carro")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable String id){
        boolean response = this.service.delete(id);
        return new ResponseEntity<Boolean>(response, HttpStatus.CREATED);
    }
}
