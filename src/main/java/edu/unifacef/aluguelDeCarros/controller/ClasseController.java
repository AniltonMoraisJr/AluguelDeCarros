package edu.unifacef.aluguelDeCarros.controller;

import edu.unifacef.aluguelDeCarros.dto.ClasseRequestDTO;
import edu.unifacef.aluguelDeCarros.dto.ClasseResponseDTO;
import edu.unifacef.aluguelDeCarros.dto.ClienteResponseDTO;
import edu.unifacef.aluguelDeCarros.service.ClasseService;
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
@RequestMapping("/classes")
@Api(value = "Classes",description = "End-points de classes", consumes="application/json")
public class ClasseController {

    @Autowired
    private ClasseService service;

    @Operation(summary = "Retorna todos as classes paginado")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Classes encontradas",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Erro ao encontrar classes",
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
    public ResponseEntity<Page<ClasseResponseDTO>> index(@RequestParam(value = "page", defaultValue = "0", required = false) int page,
                                                         @RequestParam(value = "size", defaultValue = "10", required = false) int size,
                                                         @RequestParam(value = "sort", defaultValue = "createdDate", required = false) String sort,
                                                         @RequestParam(value = "direction", defaultValue = "ASC", required = false) String direction){
        Page<ClasseResponseDTO> response = this.service.findAll(PageRequest.of(page, size, direction.equals("ASC") ? Sort.by(sort).ascending() : Sort.by(sort).descending()));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Operation(summary = "Buscar uma classe pelo o id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "302", description = "Classe encontrada",
                    content = @Content),
            @ApiResponse(responseCode = "302", description = "Classe encontrada",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Erro ao encontrar classe",
                    content = @Content) })
    @GetMapping("/{id}")
    public ResponseEntity<ClasseResponseDTO> show(@PathVariable String id){
        ClasseResponseDTO response = this.service.findById(id);
        return new ResponseEntity<>(response, HttpStatus.FOUND);
    }

    @Operation(summary = "Salvar uma nova classe")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Classe foi salvo",
                    content = @Content(schema = @Schema(implementation = ClasseResponseDTO.class))),
            @ApiResponse(responseCode = "400", description = "Classe não foi salvo. Erro na requisição",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Classe não foi salvo. Erro interno",
                    content = @Content) })
    @PostMapping
    public ResponseEntity<ClasseResponseDTO> store(@Validated @RequestBody ClasseRequestDTO request){
        ClasseResponseDTO response = this.service.save(null, request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @Operation(summary = "Atualizar uma classe")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Classe foi salvo",
                    content = @Content(schema = @Schema(implementation = ClienteResponseDTO.class))),
            @ApiResponse(responseCode = "400", description = "Classe não foi salvo. Erro na requisição",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Erro ao encontrar classe",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Classe não foi salvo. Erro interno",
                    content = @Content) })
    @PutMapping("/{id}")
    public ResponseEntity<ClasseResponseDTO> update(@PathVariable String id, @Validated @RequestBody ClasseRequestDTO request){
        ClasseResponseDTO response = this.service.save(id, request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Operation(summary = "Deletar uma classe")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Classe foi deletado",
                    content = @Content),
            @ApiResponse(responseCode = "400", description = "Classe não foi deletado. Erro na requisição",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Erro ao encontrar classe",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Classe não foi deletado. Erro interno",
                    content = @Content) })
    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable String id){
        boolean response = this.service.delete(id);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
