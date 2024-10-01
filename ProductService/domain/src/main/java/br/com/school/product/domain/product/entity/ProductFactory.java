package br.com.school.product.domain.product.entity;

import br.com.school.product.domain.exception.NotificationException;
import br.com.school.product.domain.validation.Error;
import br.com.school.product.domain.validation.NotificationValidation;

import java.math.BigDecimal;
import java.util.UUID;

public class ProductFactory {

    public static ProductEntity createProduct(String sku, String name, BigDecimal stock, BigDecimal cost, BigDecimal price) {
        String id = UUID.randomUUID().toString();
        validate(sku, name, stock, cost, price);

        return ProductEntity.create(id, sku, name, stock, cost, price);
    }

    public static ProductEntity updateProduct(ProductEntity existingProduct, String sku, String name, BigDecimal stock, BigDecimal cost, BigDecimal price) {
        validate(sku, name, stock, cost, price);
        existingProduct.update(sku, name, stock, cost, price);
        return existingProduct;
    }


    private static void validate(String sku, String name, BigDecimal stock, BigDecimal cost, BigDecimal price) {
        final var notification = NotificationValidation.create();

        if (sku == null || sku.isEmpty()) {
            notification.append(new Error("SKU não pode ser nulo ou vazio"));
        }

        final var nameLength = name != null ? name.trim().length() : 0;
        if (nameLength < 5 || nameLength > 200) {
            notification.append(new Error("O nome precisa ter no mínimo 5 e no máximo 200 caracteres"));
        }

        if (stock == null || stock.compareTo(BigDecimal.ZERO) < 0) {
            notification.append(new Error("O estoque não pode ser menor que zero"));
        }

        if (cost == null || cost.compareTo(BigDecimal.ZERO) <= 0) {
            notification.append(new Error("O custo tem que ser maior que zero"));
        }

        if (price == null || price.compareTo(cost) <= 0) {
            notification.append(new Error("O preço tem que ser maior que o custo"));
        }

        if (notification.hasErrors()) {
            throw new NotificationException("Falha ao validar o produto", notification);
        }
    }
}
