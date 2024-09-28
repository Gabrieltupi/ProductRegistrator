package br.com.school.product.domain.product;

import br.com.school.product.domain.exception.NotificationException;
import br.com.school.product.domain.validation.Error;
import br.com.school.product.domain.validation.NotificationValidation;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.UUID;

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
        selfValidate();
    }

    public static ProductEntity create(final String sku, final String name, final BigDecimal stock, final BigDecimal cost, final BigDecimal price) {
        final var id = UUID.randomUUID().toString();
        return new ProductEntity(id, sku, name, stock, cost, price);
    }

    public static ProductEntity with(ProductEntity productSource) {
        return with(productSource.getId(), productSource.getSku(), productSource.getName(), productSource.getStock(), productSource.getCost(), productSource.getPrice());
    }

    public static ProductEntity with(final String id, final String sku, final String name, final BigDecimal stock, final BigDecimal cost, final BigDecimal price) {
        return new ProductEntity(id, sku, name, stock, cost, price);
    }

    public void update(final String sku, final String name, final BigDecimal stock, final BigDecimal cost, final BigDecimal price) {
        this.sku = sku;
        this.name = name;
        this.stock = stock;
        this.cost = cost;
        this.price = price;
        selfValidate();
    }

    private void selfValidate() {
        final var notification = NotificationValidation.create();

        if (sku.isEmpty()) {
            notification.append(new Error("SKU nao pode ser nulo"));
        }

        final var nameLength = name.trim().length();
        if (nameLength < 5 || nameLength > 200) {
            notification.append(new Error("O nome precisa ter no minimo 5 e o maximo 200 caracteres"));
        }

        if (stock.compareTo(BigDecimal.ZERO) < 0) {
            notification.append(new Error("O estoque nao pode ser menor que zero"));
        }

        if (cost.compareTo(BigDecimal.ZERO) <= 0) {
            notification.append(new Error("O custo tem que ser maior que zero"));
        }

        if (price.compareTo(cost) <= 0) {
            notification.append(new Error("O preco tem que ser maior que  o custo"));
        }

        if (notification.hasErrors()) {
            throw new NotificationException("Falha ao criar novo produto", notification);
        }
    }
}
