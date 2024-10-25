package br.com.school.product.domain.entity.product;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Objects;

@Getter
@Entity
@Table(name = "product")
@NoArgsConstructor
public class ProductEntity {
    @Id
    private String id;
    private String sku;
    private String name;
    private BigDecimal stock;
    private BigDecimal cost;
    private BigDecimal price;

    private ProductEntity(final String id, final String sku, final String name, final BigDecimal stock, final BigDecimal cost, final BigDecimal price) {
        this.id = Objects.requireNonNull(id, "id: Nao pode ser nulo");
        this.sku = Objects.requireNonNull(sku, "sku: Nao pode ser nulo");
        this.name = Objects.requireNonNull(name, "name: Nao pode ser nulo");
        this.stock = Objects.requireNonNull(stock, "stock: Nao pode ser nulo");
        this.cost = Objects.requireNonNull(cost, "cost: Nao pode ser nulo");
        this.price = Objects.requireNonNull(price, "price: Nao pode ser nulo");
    }


    public static ProductEntity create(final String id, final String sku, final String name, final BigDecimal stock, final BigDecimal cost, final BigDecimal price) {
        return new ProductEntity(id, sku, name, stock, cost, price);
    }

    public void update(final String sku, final String name, final BigDecimal stock, final BigDecimal cost, final BigDecimal price) {
        this.sku = Objects.requireNonNull(sku, "sku: Nao pode ser nulo");
        this.name = Objects.requireNonNull(name, "name: Nao pode ser nulo");
        this.stock = Objects.requireNonNull(stock, "stock: Nao pode ser nulo");
        this.cost = Objects.requireNonNull(cost, "cost: Nao pode ser nulo");
        this.price = Objects.requireNonNull(price, "price: Nao pode ser nulo");
    }


}
