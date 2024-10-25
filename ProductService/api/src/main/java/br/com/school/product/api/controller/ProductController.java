package br.com.school.product.api.controller;

import br.com.school.product.domain.dto.ProductCreateDto;
import br.com.school.product.domain.dto.ProductDto;
import br.com.school.product.domain.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/product")
@Validated
@RequiredArgsConstructor
@SecurityRequirement(name = "bearerAuth")
public class ProductController {

    private final ProductService service;

    @Operation(summary = "Criar novo produto")
    @ApiResponse(responseCode = "201", description = "Realizado com sucesso")
    @ApiResponse(responseCode = "404", description = "Falha na operação")
    @PostMapping
    public ResponseEntity<ProductDto> createProduct(@Valid @RequestBody ProductCreateDto product) {
        ProductDto productCreate = service.createProduct(product);
        return ResponseEntity.status(HttpStatus.CREATED).body((productCreate));
    }

    @Operation(summary = "Buscar produto pelo id")
    @ApiResponse(responseCode = "201", description = "Realizado com sucesso")
    @ApiResponse(responseCode = "404", description = "Falha na operação")
    @GetMapping("/{id}")
    public ResponseEntity<ProductDto> getById(@PathVariable String id) {
        final var response = service.getProductById(id);
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Editar informações sobre o produto atraves do id")
    @ApiResponse(responseCode = "201", description = "Realizado com sucesso")
    @ApiResponse(responseCode = "404", description = "Falha na operação")
    @PutMapping("/{id}")
    public ResponseEntity<ProductDto> updateProduct(@PathVariable String id, @RequestBody ProductCreateDto updateProduct) {
        ProductDto productDto = service.updateProduct(id, updateProduct);
        return ResponseEntity.status(HttpStatus.OK).body(productDto);
    }

    @Operation(summary = "Busca paginada de produtos ")
    @ApiResponse(responseCode = "201", description = "Realizado com sucesso")
    @ApiResponse(responseCode = "404", description = "Falha na operação")
    @GetMapping
    public ResponseEntity<Page<ProductDto>> getAllProducts(Pageable pageable) {
        final var products = service.findAllProducts(pageable);
        return ResponseEntity.ok(products);
    }

    @Operation(summary = "Deletar produto atraves do id")
    @ApiResponse(responseCode = "201", description = "Realizado com sucesso")
    @ApiResponse(responseCode = "404", description = "Falha na operação")
    @DeleteMapping("/{id}")
    public ResponseEntity<ProductDto> deleteById(@PathVariable String id) {
        service.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }
}
