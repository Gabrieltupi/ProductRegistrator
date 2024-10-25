package br.com.school.product.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductCreateDto {
    private String sku;
    private String name;
    private BigDecimal stock;
    private BigDecimal cost;
    private BigDecimal price;
}
