package br.com.fiap.produtomvc.services;

import br.com.fiap.produtomvc.dto.CategoriaDTO;
import br.com.fiap.produtomvc.models.Categoria;
import br.com.fiap.produtomvc.repository.CategoriaRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoriaService {
    @Autowired
    private CategoriaRepository repository;

    @Transactional(readOnly = true)
    public List<CategoriaDTO> findAll() {
        List<Categoria> list = repository.findAll();
        return list.stream().map(CategoriaDTO::new).collect(Collectors.toList());
    }

    @Transactional
    public CategoriaDTO insert(CategoriaDTO dto) {
        Categoria entity = new Categoria();
        copyDtoToEntity(dto, entity);
        entity = repository.save(entity);
        return new CategoriaDTO(entity);
    }

    @Transactional(readOnly = true)
    public CategoriaDTO findById(Long id) {

        Categoria categoria = repository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("Recurso inválido - " + id)
        );
        return new CategoriaDTO(categoria);
    }

    @Transactional
    public CategoriaDTO update(Long id, CategoriaDTO dto) {

        try {
            Categoria entity = repository.getReferenceById(id);
            copyDtoToEntity(dto, entity);
            entity = repository.save(entity);
            return new CategoriaDTO(entity);
        } catch (EntityNotFoundException e) {
            throw new IllegalArgumentException("Recurso não encontrado");
        }
    }

    @Transactional
    public void delete(Long id) {

        if (!repository.existsById(id)) {
            throw new IllegalArgumentException("Recurso inválido - id: " + id);
        }
        try {
            repository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new IllegalArgumentException("Falha de integridade referencial - id: " + id);
        }
    }

    private void copyDtoToEntity(CategoriaDTO dto, Categoria entity) {
        entity.setNome(dto.getNome());
    }


}



