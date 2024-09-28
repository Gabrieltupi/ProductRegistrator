package br.com.school.product.domain.product;

import br.com.school.product.domain.exception.NotificationException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.math.BigDecimal;
import java.util.stream.Stream;

class ProductEntityTest {

    @Test
    void shouldInstanceNewProduct() {
        final var expectedSku = "1";
        final var expectedName = "product name";
        final var expectedStock = BigDecimal.valueOf(10);
        final var expectedCost = BigDecimal.valueOf(20);
        final var expectedPrice = BigDecimal.valueOf(30);

        final var product = ProductEntity.create(expectedSku, expectedName, expectedStock, expectedCost, expectedPrice);
        Assertions.assertNotNull(product.getId());
        Assertions.assertEquals(expectedSku, product.getSku());
        Assertions.assertEquals(expectedName, product.getName());
        Assertions.assertEquals(expectedStock, product.getStock());
        Assertions.assertEquals(expectedCost, product.getCost());
        Assertions.assertEquals(expectedPrice, product.getPrice());
    }

    private static Stream<Arguments> getPossiblesOfValueProduct() {
        final var bigText = "123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890";
        return Stream.of(
                Arguments.of("", "Product Name", BigDecimal.valueOf(10), BigDecimal.valueOf(20), BigDecimal.valueOf(30), "SKU nao pode ser nulo"),
                Arguments.of("1", "Name   ", BigDecimal.valueOf(10), BigDecimal.valueOf(20), BigDecimal.valueOf(30), "O nome precisa ter no minimo 5 e o maximo 200 caracteres"),
                Arguments.of("1", bigText, BigDecimal.valueOf(10), BigDecimal.valueOf(20), BigDecimal.valueOf(30), "O nome precisa ter no minimo 5 e o maximo 200 caracteres"),
                Arguments.of("1", "Product Name", BigDecimal.valueOf(-1), BigDecimal.valueOf(20), BigDecimal.valueOf(30), "O estoque nao pode ser menor que zero"),
                Arguments.of("1", "Product Name", BigDecimal.valueOf(10), BigDecimal.valueOf(0), BigDecimal.valueOf(30), "O custo tem que ser maior que zero"),
                Arguments.of("1", "Product Name", BigDecimal.valueOf(10), BigDecimal.valueOf(20), BigDecimal.valueOf(10), "O preco tem que ser maior que  o custo")
        );
    }

    @ParameterizedTest
    @MethodSource("getPossiblesOfValueProduct")
    void shouldNotInstanceNewProduct(String sku, String name, BigDecimal stock, BigDecimal cost, BigDecimal price, String message) {

        final var expectedError = Assertions.assertThrows(NotificationException.class, () -> ProductEntity.create(sku, name, stock, cost, price));
        Assertions.assertEquals(message, expectedError.getErrors().get(0).message());
    }

    @Test
    void shouldNotInstanceNewProductWhenValuesAreNull() {
        final var expectedSku = "1";
        final var expectedName = "product name";
        final var expectedStock = BigDecimal.valueOf(10);
        final var expectedCost = BigDecimal.valueOf(20);
        final var expectedPrice = BigDecimal.valueOf(30);

        final var expectedErrorSku = Assertions.assertThrows(NullPointerException.class,
                () -> ProductEntity.create(null, expectedName, expectedStock, expectedCost, expectedPrice));
        Assertions.assertEquals("sku: Nao pode ser nulo", expectedErrorSku.getMessage());

        final var expectedErrorName = Assertions.assertThrows(NullPointerException.class,
                () -> ProductEntity.create(expectedSku, null, expectedStock, expectedCost, expectedPrice));
        Assertions.assertEquals("name: Nao pode ser nulo", expectedErrorName.getMessage());

        final var expectedErrorStock = Assertions.assertThrows(NullPointerException.class,
                () -> ProductEntity.create(expectedSku, expectedName, null, expectedCost, expectedPrice));
        Assertions.assertEquals("stock: Nao pode ser nulo", expectedErrorStock.getMessage());

        final var expectedErrorCost = Assertions.assertThrows(NullPointerException.class,
                () -> ProductEntity.create(expectedSku, expectedName, expectedStock, null, expectedPrice));
        Assertions.assertEquals("cost: Nao pode ser nulo", expectedErrorCost.getMessage());

        final var expectedErrorPrice = Assertions.assertThrows(NullPointerException.class,
                () -> ProductEntity.create(expectedSku, expectedName, expectedStock, expectedCost, null));
        Assertions.assertEquals("price: Nao pode ser nulo", expectedErrorPrice.getMessage());

    }

    @Test
    void shouldInstanceNewProductAndUpdate() {
        final var expectedSku = "1";
        final var expectedName = "product name";
        final var expectedStock = BigDecimal.valueOf(10);
        final var expectedCost = BigDecimal.valueOf(20);
        final var expectedPrice = BigDecimal.valueOf(30);

        final var product = ProductEntity.create("123", "nome do produto", BigDecimal.valueOf(1), BigDecimal.valueOf(10), BigDecimal.valueOf(100));
        final var id = product.getId();
        product.update(expectedSku, expectedName, expectedStock, expectedCost, expectedPrice);

        Assertions.assertNotNull(product.getId());
        Assertions.assertEquals(id, product.getId());
        Assertions.assertEquals(expectedSku, product.getSku());
        Assertions.assertEquals(expectedName, product.getName());
        Assertions.assertEquals(expectedStock, product.getStock());
        Assertions.assertEquals(expectedCost, product.getCost());
        Assertions.assertEquals(expectedPrice, product.getPrice());
    }

    @ParameterizedTest
    @MethodSource("getPossiblesOfValueProduct")
    void shouldInstanceNewProductAndNotUpdate(String sku, String name, BigDecimal stock, BigDecimal cost, BigDecimal price, String message) {

        final var product = ProductEntity.create("123", "nome do produto", BigDecimal.valueOf(1), BigDecimal.valueOf(10), BigDecimal.valueOf(100));

        final var expectedError = Assertions.assertThrows(NotificationException.class, () -> product.update(sku, name, stock, cost, price));
        Assertions.assertEquals(message, expectedError.getErrors().get(0).message());
    }
}