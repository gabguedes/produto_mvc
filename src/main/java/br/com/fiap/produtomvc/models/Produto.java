package br.com.fiap.produtomvc.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(exclude = {"nome", "descricao", "valor", "categoria"})

@Entity
@Table(name = "tb_produto")
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//    @NotBlank(message = "Campo requerido")
//    @Size(min = 3, message = "O nome deve ter no mínimo 3 carateres")
    private String nome;

//    @NotBlank(message = "Campo requerido")
    @Column(columnDefinition = "TEXT") //para textos longos
    private String descricao;

//    @NotNull(message = "Campo requerido")
//    @Positive(message = "O valor deve ser positivo")
    private Double valor;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "categoria_id", nullable = false) //PK
    private Categoria categoria;

    //relacionamento
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "tb_produto_loja", //tabela associativa
            joinColumns = @JoinColumn(name = "produto_id"), //ref. FK - mesma entidade da classe
            inverseJoinColumns = @JoinColumn(name = "loja_id")) //ref. PK - da outra classe
    private Set<Loja> lojas = new HashSet<>(); // não permite valores duplicados

}















