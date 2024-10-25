package br.com.school.product.domain.service;

import br.com.school.product.domain.dto.ProductCreateDto;
import br.com.school.product.domain.dto.ProductDto;
import br.com.school.product.domain.entity.product.ProductEntity;
import br.com.school.product.domain.entity.product.ProductFactory;
import br.com.school.product.domain.exception.NotFoundException;
import br.com.school.product.domain.kafka.product.EventType;
import br.com.school.product.domain.kafka.product.ProductEvent;
import br.com.school.product.domain.kafka.product.ProductProducer;
import br.com.school.product.domain.repository.ProductRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository repository;
    private final ProductProducer producer;
    private final ObjectMapper objectMapper;

    public ProductDto createProduct(ProductCreateDto createDto) {
        ProductEntity productEntity = converterDto(createDto);

        ProductEntity validatedProductEntity = ProductFactory.createProduct(
                productEntity.getSku(),
                productEntity.getName(),
                productEntity.getStock(),
                productEntity.getCost(),
                productEntity.getPrice()
        );

        ProductEntity savedProduct = repository.save(validatedProductEntity);
        producer.sendProduct(ProductEvent.create(savedProduct, EventType.CREATE));

        return retornarDto(savedProduct);
    }

    public ProductEntity converterDto(ProductCreateDto dto) {
        return objectMapper.convertValue(dto, ProductEntity.class);
    }

    public ProductDto retornarDto(ProductEntity entity) {
        return objectMapper.convertValue(entity, ProductDto.class);
    }


    public ProductDto updateProduct(String id, ProductCreateDto productDto) {
        ProductEntity productEntity = objectMapper.convertValue(getProductById(id), ProductEntity.class);

        ProductEntity updatedProduct = ProductFactory.updateProduct(
                productEntity,
                productDto.getSku(),
                productDto.getName(),
                productDto.getStock(),
                productDto.getCost(),
                productDto.getPrice()
        );
        ProductEntity savedProduct = repository.save(updatedProduct);
        producer.sendProduct(ProductEvent.create(savedProduct, EventType.UPDATE));

        return retornarDto(savedProduct);
    }

    public ProductDto getProductById(String id) {
        ProductEntity productEntity = repository.findById(id).orElseThrow(() -> NotFoundException.create("Nao foi achado o produto com esse id".formatted(id)));

        return retornarDto(productEntity);
    }

    public void deleteProduct(String id) {
        ProductEntity productEntity = objectMapper.convertValue(getProductById(id), ProductEntity.class);
        repository.delete(productEntity);
        producer.sendProduct(ProductEvent.create(productEntity, EventType.DELETE));
    }

    public Page<ProductDto> findAllProducts(Pageable pageable) {
        Page<ProductEntity> productEntities = repository.findAll(pageable);
        return productEntities.map(this::retornarDto);
    }

}
