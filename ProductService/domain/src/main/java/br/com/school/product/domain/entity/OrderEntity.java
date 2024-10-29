package br.com.school.product.domain.entity;

import br.com.school.product.domain.entity.product.ProductEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Table(name = "pedido")
public class OrderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pedido")
    private Integer idOrder;

    @Column(name = "usuario_id")
    private String userId; //possivelmente chave estrangeira d euma tabela a outra

    @Column(name = "data_criacao")
    private LocalDateTime creationDate;

    @Column(name = "status")
    private Enum status; // criar enum

    @Column(name = "valor_total")
    private BigDecimal totalPrice;

    @Column(name = "metodo_pagamento")
    private Enum paymentMethod; // enum desse tambem (pix, boleto)

    @Column(name = "produtos")
    private List<ProductEntity> products; //verificar se o dto de produto ou a entidade

    @Column(name = "data_expiracao")
    private LocalDateTime expirationDate;

    @Column(name = "qr_code")
    private String qrCode;

    @Column(name = "url_boleto")
    private String urlBoleto;

    @Column(name = "pix_EMV")
    private String pixEMV;

}
