package br.com.school.product.domain.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@Table(name = "pagemento")
public class pagamentoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pagamento")
    private Integer idPagamento;

    @Column(name = "pedido_id")
    private String pedido_id;

    @Column(name = "metodo_pagamento")
    private Enum metodoPagamento; // enum desse tambem (pix, boleto)

    @Column(name = "status_pagamento")
    private Enum statusPagamento;

    @Column(name = "data_pagamento")
    private LocalDateTime dataPagamento;

    @Column(name = "valor_pago")
    private BigDecimal valorPago;

    @Column(name = "pix_EMV")
    private String pixEMV;

    @Column(name = "qr_code")
    private String qrCode;

    @Column(name = "chave_pix")
    private String chavePix;

    @Column(name = "comprovante_pagemento")
    private String comprovantePagemento;
}
