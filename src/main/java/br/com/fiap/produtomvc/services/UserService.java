package br.com.fiap.produtomvc.services;

import br.com.fiap.produtomvc.models.User;
import br.com.fiap.produtomvc.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    @Transactional(readOnly = true)
    public List<User> findAll(){
        return repository.findAll();
    }

    @Transactional
    public User insert(User user){
        return repository.save(user);
    }

    @Transactional(readOnly = true)
    public User findById(Long id){

        User user = repository.findById(id).orElseThrow(
                ()-> new IllegalArgumentException("Recurso inválido - ID: " + id)
        );

        return user;
    }

    @Transactional
    public User update(Long id, User entity){
        try{
            User user = repository.getReferenceById(id);
            copyToProduto(entity, user);
            user = repository.save(user);
            return user;
        }catch (EntityNotFoundException e ){
            throw new IllegalArgumentException("Recurso não encontrado");
        }
    }

    private void copyToProduto(User entity, User user) {
        user.setNome(entity.getNome());
        user.setEmail(entity.getEmail());
        user.setDataNascimento(entity.getDataNascimento());
    }

    @Transactional
    public void delete(Long id){
        if(!repository.existsById(id)){
            throw new IllegalArgumentException("Usuário inválido - id: " + id);
        }
        try {
            repository.deleteById(id);
        } catch (Exception e){
            throw new IllegalArgumentException("Usuário inválido - id: " + id);
        }
    }
    
}
