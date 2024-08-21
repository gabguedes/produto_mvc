package br.com.fiap.produtomvc.services;

import br.com.fiap.produtomvc.dto.ProdutoDTO;
import br.com.fiap.produtomvc.models.Loja;
import br.com.fiap.produtomvc.models.Produto;
import br.com.fiap.produtomvc.repository.LojaRepository;
import br.com.fiap.produtomvc.repository.ProdutoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProdutoService {
    
    @Autowired
    private ProdutoRepository repository;

    @Autowired
    private LojaRepository lojaRepository;
    
    @Transactional(readOnly = true)
    public List<ProdutoDTO> findAll() {
        List<Produto> list = repository.findAll();
        return list.stream().map(ProdutoDTO::new).collect(Collectors.toList());
    }

    @Transactional
    public ProdutoDTO insert(ProdutoDTO dto) {
        Produto entity = new Produto();
        copyDtoToEntity(dto, entity);
        entity = repository.save(entity);
        return new ProdutoDTO(entity);
    }

    @Transactional(readOnly = true)
    public ProdutoDTO findById(Long id) {

        Produto produto = repository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("Recurso inválido - " + id)
        );
        return new ProdutoDTO(produto);
    }

    @Transactional
    public ProdutoDTO update(Long id, ProdutoDTO dto) {
        try {
            Produto produto = repository.getReferenceById(id);
            copyDtoToEntity(dto, produto);
            produto = repository.save(produto);
            return new ProdutoDTO(produto);
        } catch (EntityNotFoundException e) {
            throw new IllegalArgumentException("Recurso não encontrado");
        }
    }

    @Transactional
    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new IllegalArgumentException("Produto inválido - id: " + id);
        }
        try {
            repository.deleteById(id);
        } catch (Exception e) {
            throw new IllegalArgumentException("Produto inválido - id: " + id);
        }
    }

    private void copyDtoToEntity(ProdutoDTO dto, Produto entity) {
        entity.setNome(dto.getNome());
        entity.setDescricao(dto.getDescricao());
        entity.setValor(dto.getValor());
        entity.setCategoria(dto.getCategoria());

        entity.getLojas().clear();
        for(Loja item: dto.getLojas()){
            //para colocar os dados completos da loja
            Loja loja = lojaRepository.getReferenceById(item.getId());
            entity.getLojas().add(loja);
            String fodase = RandomStringUtils.randomAlphanumeric(6)
        }
    }


}



