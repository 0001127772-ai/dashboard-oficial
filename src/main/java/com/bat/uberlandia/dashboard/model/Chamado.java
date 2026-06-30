package com.bat.uberlandia.dashboard.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "chamados")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Chamado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(nullable = false, length = 200)
    private String titulo;

    @Column(length = 1000)
    private String descricao;

    @Column(length = 500)
    private String caminhoFoto;

    public enum Status {
        ABERTO,
        EM_ANDAMENTO,
        PAUSADO,
        CONCLUIDO

    }
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    @Builder.Default
    private Status status = Status.ABERTO;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "maquina_id", nullable = false)
    @ToString.Exclude
    private Maquina maquina;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tecnico_id")
    @ToString.Exclude
    private Usuario tecnico;

    private LocalDateTime dataAbertura;
    private LocalDateTime dataConclusao;

    @PrePersist
    public void PrePersist() {
        if(dataAbertura == null){
            dataAbertura = LocalDateTime.now();
        }
    }
}
