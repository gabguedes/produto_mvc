package br.com.fiap.produtomvc.controllers;

import br.com.fiap.produtomvc.models.Categoria;
import br.com.fiap.produtomvc.models.User;
import br.com.fiap.produtomvc.repository.ProdutoRepository;
import br.com.fiap.produtomvc.repository.UserRepository;
import br.com.fiap.produtomvc.services.CategoriaService;
import br.com.fiap.produtomvc.services.ProdutoService;
import br.com.fiap.produtomvc.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/usuarios")
public class UserController {
    
    @Autowired
    private UserService service;

    @Autowired
    private UserRepository repository;

    //Form para cadastro
    //URL - localhost:8080/usuarios/form
    //@GetMapping("/novo")
    @GetMapping("/form")
    public String loadForm(Model model) {
        model.addAttribute("user", new User());
        return "user/novo-user";
    }

    // HTTP - POST -  http:localhost:8080/usuarios

    //@PostMapping("/salvar")
    @PostMapping()
    @Transactional
    public String insert(@Valid User user,
                         BindingResult result,
                         RedirectAttributes attributes) {
        if(result.hasErrors()){
            return "user/novo-user";
        }
        user = service.insert(user);
        attributes.addFlashAttribute("mensagem", "User salvo com sucesso");
        return "redirect:/usuarios/form";
    }

    // HTTP - GET -  http:localhost:8080/usuarios
    //@GetMapping("/listar")
    @GetMapping()
    @Transactional(readOnly = true)
    public String findAll(Model model){
        model.addAttribute("usuarios", service.findAll());
        return "/user/listar-usuarios";
    }

    // HTTP - GET -  http:localhost:8080/usuarios/1
    //  @GetMapping("/editar/{id}")
    @GetMapping("/{id}")
    @Transactional(readOnly = true)
    public String findById(@PathVariable("id") Long id, Model model ){

        User user = service.findById(id);

        model.addAttribute("user", user);
        return "/user/editar-user";
    }

    // HTTP - POST -  http:localhost:8080/usuarios/1
    // @PostMapping("/editar/{id}")
    @PutMapping("/{id}")
    @Transactional
    public String update(@PathVariable("id") Long id,
                         @Valid User user,
                         BindingResult result){
        if(result.hasErrors()){
            user.setId(id);
            return "/user/editar-user";
        }
        service.update(id, user);
        return "redirect:/usuarios";
    }

    //  @GetMapping("/deletar/{id}")
    @DeleteMapping("/{id}")
    @Transactional
    public String delete(@PathVariable("id") Long id, Model model){

        service.delete(id);
        return "redirect:/usuarios";
    }
}
