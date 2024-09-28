package br.com.school.product.api.product;

import br.com.school.product.domain.product.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService service;

    @Operation(summary = "Criar novo produto")
    @ApiResponse(responseCode = "201", description = "Realizado com sucesso")
    @ApiResponse(responseCode = "404", description = "Falha na operação")
    @PostMapping
    public ResponseEntity<ProductResponse> createProduct(@RequestBody ProductRequest request) {
        final var productEntity = request.toEntity();
        final var saveProduct = service.createProduct(productEntity);
        final var productResponse = ProductResponse.fromEntity(saveProduct);
        return ResponseEntity.status(HttpStatus.CREATED).body(productResponse);
    }

    @Operation(summary = "Buscar produto pelo id")
    @ApiResponse(responseCode = "201", description = "Realizado com sucesso")
    @ApiResponse(responseCode = "404", description = "Falha na operação")
    @GetMapping("/{id}")
    public ResponseEntity<ProductResponse> getById(@PathVariable String id) {
        final var product = service.getProductById(id);
        final var response = ProductResponse.fromEntity(product);
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Editar informações sobre o produto atraves do id")
    @ApiResponse(responseCode = "201", description = "Realizado com sucesso")
    @ApiResponse(responseCode = "404", description = "Falha na operação")
    @PutMapping("/{id}")
    public ResponseEntity<ProductResponse> updateProduct(@PathVariable String id, @RequestBody ProductRequest request) {
        final var productFromBd = service.getProductById(id);
        final var productToUpdate = request.toEntity(id);
        final var saveProduct = service.updateProduct(productFromBd, productToUpdate);
        final var productResponse = ProductResponse.fromEntity(saveProduct);
        return ResponseEntity.status(HttpStatus.CREATED).body(productResponse);
    }

    @Operation(summary = "Busca paginada de produtos ")
    @ApiResponse(responseCode = "201", description = "Realizado com sucesso")
    @ApiResponse(responseCode = "404", description = "Falha na operação")
    @GetMapping
    public ResponseEntity<Page<ProductResponse>> getAllProducts(Pageable pageable) {
        final var products = service.findAllProducts(pageable);
        final var responsePage = products.map(ProductResponse::fromEntity);
        return ResponseEntity.ok(responsePage);
    }

    @Operation(summary = "Deletar produto atraves do id")
    @ApiResponse(responseCode = "201", description = "Realizado com sucesso")
    @ApiResponse(responseCode = "404", description = "Falha na operação")
    @DeleteMapping("/{id}")
    public ResponseEntity<ProductResponse> deleteById(@PathVariable String id) {
        service.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }

}
