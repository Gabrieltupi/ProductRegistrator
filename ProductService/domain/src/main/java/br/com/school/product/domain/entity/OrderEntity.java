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
    private String idPedido;

    @Column(name = "usuario_id")
    private String usuarioId; //possivelmente chave estrangeira d euma tabela a outra

    @Column(name = "data_criacao")
    private LocalDateTime dataCriacao;

    @Column(name = "status")
    private Enum status; // criar enum

    @Column(name = "valor_total")
    private BigDecimal valorTotal;

    @Column(name = "metodo_pagamento")
    private Enum metodoPagamento; // enum desse tambem (pix, boleto)

    @Column(name = "produtos")
    private List<ProductEntity> produtos; //verificar se o dto de produto ou a entidade

    @Column(name = "data_expiracao")
    private LocalDateTime dataExpiracao;

    @Column(name = "qr_code")
    private String qrCode;

    @Column(name = "url_boleto")
    private String urlBoleto;

    @Column(name = "pix_EMV")
    private String pixEMV;

}
