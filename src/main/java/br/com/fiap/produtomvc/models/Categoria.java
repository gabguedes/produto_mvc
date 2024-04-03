package br.com.fiap.produtomvc.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

//anotações javaBeans
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(exclude = {"nome"})
@Entity
@Table(name = "tb_categoria")
public class Categoria {

    //atributos
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
//    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "categoria_seq")
//    @SequenceGenerator(name = "categoria_seq", sequenceName = "categoria_seq", allocationSize = 1)
    private Long id;

    @NotBlank(message =" campo requerido!")
    @Size(min = 3, message = "O nome deve ter nome mínimo 3 caracteres.")
//    @Column(length = 150, nullable = false)
    private String nome;

    @OneToMany(mappedBy = "categoria")
    private List<Produto> produtos = new ArrayList<>();
}
