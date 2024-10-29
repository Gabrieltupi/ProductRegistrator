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
public class PaymentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pagamento")
    private Integer idPayment;

    @Column(name = "pedido_id")
    private String orderId;

    @Column(name = "metodo_pagamento")
    private Enum paymentMethod; // enum desse tambem (pix, boleto)

    @Column(name = "status_pagamento")
    private Enum paymentStatus;

    @Column(name = "data_pagamento")
    private LocalDateTime paymentDate;

    @Column(name = "valor_pago")
    private BigDecimal totalPaid;

    @Column(name = "pix_EMV")
    private String pixEMV;

    @Column(name = "qr_code")
    private String qrCode;

    @Column(name = "chave_pix")
    private String keyPix;

    @Column(name = "comprovante_pagemento")
    private String proofPayment;
}
