package br.com.fiap.produtomvc.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

@Entity
@Table(name = "tb_loja")
public class Loja {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//    @NotBlank(message = "Campo requerido")
//    @Size(min = 3, message = "O nome deve ter no mínimo 3 carateres")
    private String nome;

    //relacionamento
    //mapeamento na outra tabela
    @ManyToMany(mappedBy = "lojas", fetch = FetchType.EAGER)
    private Set<Produto> produtos = new HashSet<>(); // não permite valores duplicados

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Loja loja = (Loja) o;
        return Objects.equals(id, loja.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}

















