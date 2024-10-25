package br.com.school.product.domain.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@Table(name = "pedido")
public class pedidoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pedido")
    private String idPedido;

    @Column(name = "usuario_id")
    private String usuarioId;

    @Column(name = "data_criacao")
    private LocalDateTime dataCriacao;

    @Column(name = "status")
    private Enum status;

    
}
