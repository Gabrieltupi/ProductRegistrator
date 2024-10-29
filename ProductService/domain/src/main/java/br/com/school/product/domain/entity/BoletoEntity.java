package br.com.school.product.domain.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@Table(name = "boleto")
public class BoletoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_boleto")
    private Integer idBoleto;

    @Column(name = "pedido_id")
    private String orderId;

    @Column(name = "codigo_barras")
    private String barcode;

    @Column(name = "boleto_url")
    private String boletoUrl;

    @Column(name = "data_vencimento")
    private LocalDateTime dueDate;

    @Column(name = "status")
    private Enum status;


}
