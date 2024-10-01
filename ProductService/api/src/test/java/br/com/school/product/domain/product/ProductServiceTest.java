//package br.com.school.product.domain.product;
//
//import br.com.school.product.domain.kafka.product.EventType;
//import br.com.school.product.domain.kafka.product.ProductProducer;
//import br.com.school.product.domain.product.entity.ProductEntity;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.Mockito;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.springframework.data.domain.PageImpl;
//import org.springframework.data.domain.Pageable;
//
//import java.math.BigDecimal;
//import java.util.List;
//import java.util.Objects;
//import java.util.Optional;
//
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.ArgumentMatchers.argThat;
//import static org.mockito.Mockito.*;
//
//@ExtendWith(MockitoExtension.class)
//class ProductServiceTest {
//
//    @InjectMocks
//    private ProductService service;
//
//    @Mock
//    private ProductRepository repository;
//
//    @Mock
//    private ProductProducer producer;
//
//    @Test
//    void shouldCreateProduct() {
//        final var expectedSku = "1";
//        final var expectedName = "product name";
//        final var expectedStock = BigDecimal.valueOf(10);
//        final var expectedCost = BigDecimal.valueOf(20);
//        final var expectedPrice = BigDecimal.valueOf(30);
//
//        final var product = ProductEntity.create(expectedSku, expectedName, expectedStock, expectedCost, expectedPrice);
//        final var expectedId = product.getId();
//        final var expectedEventType = EventType.CREATE;
//
//        when(repository.save(any(ProductEntity.class))).thenReturn(product);
//        Mockito.doNothing().when(producer).sendProduct(any());
//
//        service.createProduct(product);
//
//        Mockito.verify(repository, times(1)).save(argThat(arg ->
//                Objects.equals(expectedSku, arg.getSku()) &&
//                        Objects.equals(expectedName, arg.getName()) &&
//                        Objects.equals(expectedStock, arg.getStock()) &&
//                        Objects.equals(expectedCost, arg.getCost()) &&
//                        Objects.equals(expectedPrice, arg.getPrice())
//        ));
//        verify(producer, times(1)).sendProduct(argThat(arg ->
//                Objects.equals(expectedSku, arg.sku()) &&
//                        Objects.equals(expectedName, arg.name()) &&
//                        Objects.equals(expectedStock, arg.stock()) &&
//                        Objects.equals(expectedId, arg.id()) &&
//                        Objects.equals(expectedEventType, arg.eventType())
//
//        ));
//    }
//
//    @Test
//    void shouldUpdateProduct() {
//        final var expectedSku = "1";
//        final var expectedName = "product name";
//        final var expectedStock = BigDecimal.valueOf(10);
//        final var expectedCost = BigDecimal.valueOf(20);
//        final var expectedPrice = BigDecimal.valueOf(30);
//
//        final var productFromBd = ProductEntity.create("123", "nome do produto", BigDecimal.valueOf(1), BigDecimal.valueOf(10), BigDecimal.valueOf(100));
//        final var product = ProductEntity.with(productFromBd.getId(), expectedSku, expectedName, expectedStock, expectedCost, expectedPrice);
//
//        final var expectedId = productFromBd.getId();
//        final var expectedEventType = EventType.UPDATE;
//
//        when(repository.save(any(ProductEntity.class))).thenReturn(product);
//        Mockito.doNothing().when(producer).sendProduct(any());
//
//        service.updateProduct(productFromBd, product);
//
//        Mockito.verify(repository, times(1)).save(argThat(arg ->
//                Objects.equals(expectedSku, arg.getSku()) &&
//                        Objects.equals(expectedName, arg.getName()) &&
//                        Objects.equals(expectedStock, arg.getStock()) &&
//                        Objects.equals(expectedCost, arg.getCost()) &&
//                        Objects.equals(expectedPrice, arg.getPrice())
//        ));
//        verify(producer, times(1)).sendProduct(argThat(arg ->
//                Objects.equals(expectedSku, arg.sku()) &&
//                        Objects.equals(expectedName, arg.name()) &&
//                        Objects.equals(expectedStock, arg.stock()) &&
//                        Objects.equals(expectedId, arg.id()) &&
//                        Objects.equals(expectedEventType, arg.eventType())
//
//        ));
//    }
//
//    @Test
//    void shouldGetProductById() {
//        final var expectedSku = "1";
//        final var expectedName = "product name";
//        final var expectedStock = BigDecimal.valueOf(10);
//        final var expectedCost = BigDecimal.valueOf(20);
//        final var expectedPrice = BigDecimal.valueOf(30);
//
//        final var product = ProductEntity.create(expectedSku, expectedName, expectedStock, expectedCost, expectedPrice);
//
//        when(repository.findById(any())).thenReturn((Optional.of(product)));
//
//        final var actualProduct = service.getProductById("1");
//
//        Assertions.assertTrue(
//                Objects.equals(expectedSku, actualProduct.getSku()) &&
//                        Objects.equals(expectedName, actualProduct.getName()) &&
//                        Objects.equals(expectedStock, actualProduct.getStock()) &&
//                        Objects.equals(expectedCost, actualProduct.getCost()) &&
//                        Objects.equals(expectedPrice, actualProduct.getPrice())
//        );
//
//        Mockito.verify(repository, times(1)).findById("1");
//    }
//
//    @Test
//    void shouldDeleteProduct() {
//        final var expectedSku = "1";
//        final var expectedName = "product name";
//        final var expectedStock = BigDecimal.valueOf(10);
//        final var expectedCost = BigDecimal.valueOf(20);
//        final var expectedPrice = BigDecimal.valueOf(30);
//
//        final var product = ProductEntity.create(expectedSku, expectedName, expectedStock, expectedCost, expectedPrice);
//        final var expectedId = product.getId();
//        final var expectedEventType = EventType.DELETE;
//
//        when(repository.findById(any())).thenReturn((Optional.of(product)));
//        Mockito.doNothing().when(repository).delete(any());
//        Mockito.doNothing().when(producer).sendProduct(any());
//
//        service.deleteProduct("1");
//
//        Mockito.verify(repository, times(1)).findById("1");
//        Mockito.verify(repository, times(1)).delete(any());
//
//        verify(producer, times(1)).sendProduct(argThat(arg ->
//                Objects.equals(expectedSku, arg.sku()) &&
//                        Objects.equals(expectedName, arg.name()) &&
//                        Objects.equals(expectedStock, arg.stock()) &&
//                        Objects.equals(expectedId, arg.id()) &&
//                        Objects.equals(expectedEventType, arg.eventType())
//
//        ));
//    }
//
//    @Test
//    void shouldFindAll() {
//        final var expectedSku = "1";
//        final var expectedName = "product name";
//        final var expectedStock = BigDecimal.valueOf(10);
//        final var expectedCost = BigDecimal.valueOf(20);
//        final var expectedPrice = BigDecimal.valueOf(30);
//
//        final var product = ProductEntity.create(expectedSku, expectedName, expectedStock, expectedCost, expectedPrice);
//        final var page = new PageImpl<ProductEntity>(List.of(product));
//
//        when(repository.findAll(any(org.springframework.data.domain.Pageable.class))).thenReturn(page);
//
//        final var pageReturn = service.findAllProducts(Pageable.ofSize(1));
//        Assertions.assertNotNull(pageReturn);
//        Assertions.assertEquals(product.getId(), pageReturn.get().toList().get(0).getId());
//
//
//        Mockito.verify(repository, times(1)).findAll(any(org.springframework.data.domain.Pageable.class));
//
//    }
//}